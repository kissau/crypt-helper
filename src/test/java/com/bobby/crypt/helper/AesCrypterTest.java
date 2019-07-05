package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.AesCrypter;

import junit.framework.TestCase;

public class AesCrypterTest extends TestCase{
	
	public void test() {
		AesCrypter aesCrypter = new AesCrypter();
		String encrypt = aesCrypter.encrypt("你好时间段上课自行车想象中只需再初学者从自行车自行车在呈现出行政村", "ddd");
		System.err.println(encrypt);
		
		String src = aesCrypter.decrypt(encrypt, "ddd");
		System.err.println(src);
	}

}
