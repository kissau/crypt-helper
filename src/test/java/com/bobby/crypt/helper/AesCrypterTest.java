package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.AesCrypter;

import junit.framework.TestCase;

public class AesCrypterTest extends TestCase{
	
	public void test01() {
		AesCrypter aesCrypter = new AesCrypter();
		String src = "helloworld";
		String encrypt = aesCrypter.encrypt(src, "key");
		String decryptSrc = aesCrypter.decrypt(encrypt, "key");
		assertEquals(src, decryptSrc);
	}
	
	public void test02() {
		AesCrypter aesCrypter = new AesCrypter();
		String src = "helloworld";
		String encrypt = aesCrypter.encrypt(src, "key");
		String decryptSrc = aesCrypter.decrypt(encrypt, "key2");
		assertNotSame(src, decryptSrc);
	}

}
