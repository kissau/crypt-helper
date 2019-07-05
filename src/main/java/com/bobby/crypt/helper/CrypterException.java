package com.bobby.crypt.helper;

public class CrypterException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3934297284343833126L;
	
	public CrypterException(String message) {
		super(message);
	}
	
	public CrypterException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CrypterException(Throwable cause) {
        super(cause);
    }

}
