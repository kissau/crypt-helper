package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.MessageDigestCrypter;

import junit.framework.TestCase;

public class MessageDigestCrypterTest extends TestCase{
	
	public void test() {
		MessageDigestCrypter messageDigestCrypter = new MessageDigestCrypter("md5");
		String s = messageDigestCrypter.encrypt("test", "abc.1234");
		System.err.println(s);
		
		String s2 = messageDigestCrypter.encrypt("testa", "bc.1234");
		System.err.println(s2);
	}

}
