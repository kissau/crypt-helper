package com.bobby.crypt.helper.internal;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.bobby.crypt.helper.Crypter;
import com.bobby.crypt.helper.CrypterException;

public class MessageDigestCrypter implements Crypter{
	
	private MessageDigest messageDigest;
	
	/**
	 * 
	 * @param algorithm 可选Md5和Sha1
	 */
	public MessageDigestCrypter(String algorithm) {
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new CrypterException(e);
		}
	}

	@Override
	public String encrypt(String src, String key) {
		try {
			byte[] srcBytes = Base64.getEncoder().encode(src.getBytes(CHARSET));
			byte[] keyBytes = Base64.getEncoder().encode(key.getBytes(CHARSET));
			return Base64.getEncoder().encodeToString(encrypt(srcBytes, keyBytes));
		} catch (UnsupportedEncodingException e) {
			throw new CrypterException("不支持编码格式："+CHARSET);
		}
	}
	
	@Override
	public String decrypt(String src, String key) throws CrypterException {
		throw new CrypterException("MessageDigest不支持解密");
	}

	@Override
	public byte[] encrypt(byte[] srcBytes, byte[] keyBytes) {
		byte[] array = new byte[srcBytes.length + keyBytes.length];
		System.arraycopy(srcBytes, 0, array, 0, srcBytes.length);
		System.arraycopy(keyBytes, 0, array, srcBytes.length, keyBytes.length);
		return messageDigest.digest(array);
	}

	@Override
	public byte[] decrypt(byte[] srcBytes, byte[] key) throws CrypterException {
		throw new CrypterException("MessageDigest不支持解密");
	}

}
