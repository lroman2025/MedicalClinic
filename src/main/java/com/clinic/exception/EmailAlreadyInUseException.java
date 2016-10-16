package com.clinic.exception;

public class EmailAlreadyInUseException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmailAlreadyInUseException() {
		super("User with this email already exists.");
	}

	public EmailAlreadyInUseException(String messsage) {
		super(messsage);
	}
}
