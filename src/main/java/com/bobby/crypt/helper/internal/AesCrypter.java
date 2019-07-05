package com.bobby.crypt.helper.internal;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.bobby.crypt.helper.Crypter;
import com.bobby.crypt.helper.CrypterException;

/**
 * Aes对称加密
 */
public class AesCrypter implements Crypter{
	
	private Cipher cipher;
	
	public AesCrypter() {
		try {
			cipher = Cipher.getInstance("AES/CFB/NoPadding");
		} catch (Exception e) {
			throw new CrypterException(e);
		}
	}
	
	@Override
	public String encrypt(String src, String key) {
		try {
			return Base64.getEncoder().encodeToString(encrypt(src.getBytes(CHARSET), key.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new CrypterException("不支持编码格式："+CHARSET);
		}
	}

	@Override
	public String decrypt(String src, String key) throws CrypterException {
		try {
			byte[] srcBytes = Base64.getDecoder().decode(src);
			return new String(decrypt(srcBytes, key.getBytes(CHARSET)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new CrypterException("不支持编码格式："+CHARSET);
		}
	}

	@Override
	public byte[] encrypt(byte[] srcBytes, byte[] keyBytes) {
		try {
			byte[] encryptKeys = getValidkeyBytes(keyBytes);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(encryptKeys);
			SecretKeySpec keySpec = new SecretKeySpec(encryptKeys, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
			return cipher.doFinal(srcBytes);
		}catch (Exception e) {
			throw new CrypterException(e);
		}
	}

	@Override
	public byte[] decrypt(byte[] srcBytes, byte[] keyBytes) throws CrypterException {
		try {
			byte[] encryptKeys = getValidkeyBytes(keyBytes);
			SecretKeySpec keySpec = new SecretKeySpec(encryptKeys, "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(encryptKeys);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
			return cipher.doFinal(srcBytes);
		}catch (Exception e) {
			throw new CrypterException(e);
		}
	}
	
	private byte[] getValidkeyBytes(byte[] keyBytes) {
		byte[] bs = Base64.getEncoder().encode(keyBytes);
		while(bs.length < 16) {
			bs = Base64.getEncoder().encode(bs);
		}
		if(bs.length > 16) {
			byte[] dest = new byte[16];
			System.arraycopy(bs, 0, dest, 0, 16);
			return dest;
		}
		return bs;
	}

}
