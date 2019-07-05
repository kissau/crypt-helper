package com.bobby.crypt.helper;

/**
 * 加密解密器
 */
public interface Crypter {
	
	static final String CHARSET = "UTF-8";
	
	/**
	 * 加密
	 * @param src 原文
	 * @param key 加密秘钥
	 * @return 密文
	 */
	public String encrypt(String src, String key);
	
	/**
	 * 解密
	 * @param cipher 密文
	 * @param key 借密秘钥
	 * @return 原文
	 */
	public String decrypt(String cipher, String key);
	
	/**
	 * @see #encrypt(String, String)
	 */
	public byte[] encrypt(byte[] srcBytes, byte[] keyBytes);
	
	/**
	 * @see #decrypt(String, String)
	 */
	public byte[] decrypt(byte[] cipherBytes, byte[] keyBytes);

}
