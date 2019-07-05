package com.bobby.crypt.helper;

import com.bobby.crypt.helper.internal.RsaCrypter;

import junit.framework.TestCase;

public class RsaCrypterTest extends TestCase{
	
	public void test() {
		RsaCrypter rsaCrypter = new RsaCrypter();
		String cipherBytes = rsaCrypter.encrypt("你好", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG77PYUAcCpANyUmsHJfuDIia9FcITsuu9lnfbE2BbEwd4SOxPBFTwEWTZ9/e+mtjP97KFEBohGkwy+VHE5KocypBv0O7YgEevwMgpvxyYY0v104CB/k0yjCFV7lc7FxY5VgEKrMiXTIkMr1ukCnWVvapvRCS6IFcsT/kkjPgfDQIDAQAB"); 
		System.err.println(cipherBytes);
		String old = rsaCrypter.decrypt(cipherBytes, "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIbvs9hQBwKkA3JSawcl+4MiJr0VwhOy672Wd9sTYFsTB3hI7E8EVPARZNn3976a2M/3soUQGiEaTDL5UcTkqhzKkG/Q7tiAR6/AyCm/HJhjS/XTgIH+TTKMIVXuVzsXFjlWAQqsyJdMiQyvW6QKdZW9qm9EJLogVyxP+SSM+B8NAgMBAAECgYEAhj0FH9dNghUE0MCpdS0WL/jTrRxuPQase6mrhyiZnUErF0EExf87OLE1MZr8voRx2UNEOBgyxmfREozyCfyqNg1OdGYEHSyuJ9wglkhq8GVYO8IzI29Mqej0MSprtsE0BPAKBHRU/DWP19ej5bv5ZnAhLs10K7uVEsuGwJJYcMECQQDibedUr7tnGfojyjFY0vCAaVwgS0vXfno7WQyAXUz0Fv8Uy1q9nyF0RrkeA8BOk7S4ljE77ufX0rr2qL7kHW8pAkEAmI718EnQCKKJUjrQUl4iG/lYoNwW2QnxTGZmESyFwkS95PTt8K4GVHpICqRNP1JJBNxVSEVts/eA4zrxPAoBRQJBAJxxEsOQJwq1B/5yVGXqWABgyyYE4AGjgRBAFkMaM3Dx8ouLdMZOi+6qbnwuW0/u/Y4LNzkRd13GWybQsBMrwwECQEULptmavpG55kaWIcS1n+BjSK59DcYrDs+SJK2vJdaXwA4IoEvmpyzCrypJ1EBNYIjXo61y5sSlxuqQua9/o7UCQGYdM3/mF/FEC3wxdfQq0Pw/Pwn8RQxg1natRfoTyzOJDfE/YUYGjIEe2pQtDI1s+IRCwrXOB0cySbpaSHCjr5U=");
		System.err.println(new String(old));
	}

}
