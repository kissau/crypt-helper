package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.MessageDigestCrypter;

import junit.framework.TestCase;

public class MessageDigestCrypterTest extends TestCase{
	
	public void test() {
		MessageDigestCrypter messageDigestCrypter = new MessageDigestCrypter("md5");
		String encrypt1 = messageDigestCrypter.encrypt("test", "abc.1234");
		String encrypt2 = messageDigestCrypter.encrypt("testa", "bc.1234");
		assertNotSame(encrypt1, encrypt2);
	}

}
