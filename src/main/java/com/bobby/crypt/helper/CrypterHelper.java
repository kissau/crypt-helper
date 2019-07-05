package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.AesCrypter;
import com.bobby.crypt.helper.internal.MessageDigestCrypter;
import com.bobby.crypt.helper.internal.RsaCrypter;

public class CrypterHelper {
	
	public static Crypter getCrypter(String algorithm) throws CrypterException{
		if("aes".equalsIgnoreCase(algorithm)) {
			return new AesCrypter();
		}else if("rsa".equalsIgnoreCase(algorithm)) {
			return new RsaCrypter();
		}else if("md5".equalsIgnoreCase(algorithm) 
				|| "sha1".equalsIgnoreCase(algorithm)) {
			return new MessageDigestCrypter(algorithm.toUpperCase());
		}
		
		throw new CrypterException("暂时不支持算法" + algorithm);
	}

}
