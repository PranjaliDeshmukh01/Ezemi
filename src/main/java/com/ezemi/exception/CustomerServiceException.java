package com.ezemi.exception;

public class CustomerServiceException extends RuntimeException {
	

	public CustomerServiceException() {
		super();
	}
	
	public CustomerServiceException(String msg) {
		super(msg);
	}

}
