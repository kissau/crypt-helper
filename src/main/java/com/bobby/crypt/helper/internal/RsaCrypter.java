package com.bobby.crypt.helper.internal;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import com.bobby.crypt.helper.Crypter;
import com.bobby.crypt.helper.CrypterException;

/**
 * Rsa非对称加密
 */
public class RsaCrypter implements Crypter{
	
	private Cipher cipher;
	
	private KeyFactory keyFactory;
	
	public RsaCrypter() {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			keyFactory = KeyFactory.getInstance("RSA");
		}catch (Exception e) {
			throw new CrypterException(e);
		}
	}
	
	@Override
	public String encrypt(String src, String key) {
		try {
			return Base64.getEncoder().encodeToString(encrypt(src.getBytes(CHARSET), Base64.getDecoder().decode(key)));
		} catch (UnsupportedEncodingException e) {
			throw new CrypterException("不支持编码格式："+CHARSET);
		}
	}

	@Override
	public String decrypt(String src, String key) throws CrypterException {
		try {
			byte[] srcBytes = Base64.getDecoder().decode(src);
			return new String(decrypt(srcBytes, Base64.getDecoder().decode(key)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new CrypterException("不支持编码格式："+CHARSET);
		}
	}

	@Override
	/**
	 * 利用公钥加密
	 */
	public byte[] encrypt(byte[] srcBytes, byte[] keyBytes) {
		try {
            RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int size = publicKey.getModulus().bitLength() / 8 - 11;
            ByteArrayOutputStream baos = new ByteArrayOutputStream((srcBytes.length + size - 1) / size * (size + 11));
            int left = 0;
            for (int i = 0; i < srcBytes.length; ) {
                left = srcBytes.length - i;
                if (left > size) {
                    cipher.update(srcBytes, i, size);
                    i += size;
                } else {
                    cipher.update(srcBytes, i, left);
                    i += left;
                }
                baos.write(cipher.doFinal());
            }
            return baos.toByteArray();
        } catch (Exception e) {
            throw new CrypterException(e);
        }
	}

	@Override
	/**
	 * 利用私钥解密
	 */
	public byte[] decrypt(byte[] cipherBytes, byte[] keyBytes) {
        try {
            RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey)keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int size = privateKey.getModulus().bitLength() / 8;
            ByteArrayOutputStream baos = new ByteArrayOutputStream((cipherBytes.length + size - 12) / (size - 11) * size);
            int left = 0;
            for (int i = 0; i < cipherBytes.length; ) {
                left = cipherBytes.length - i;
                if (left > size) {
                    cipher.update(cipherBytes, i, size);
                    i += size;
                } else {
                    cipher.update(cipherBytes, i, left);
                    i += left;
                }
                baos.write(cipher.doFinal());
            }
            return baos.toByteArray();
        } catch (Exception e) {
        	throw new CrypterException(e);
        }
	}

}
