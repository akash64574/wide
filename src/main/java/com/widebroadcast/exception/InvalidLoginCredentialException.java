package com.widebroadcast.exception;

public class InvalidLoginCredentialException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidLoginCredentialException(String exception) {
		super(exception);
	}
}
