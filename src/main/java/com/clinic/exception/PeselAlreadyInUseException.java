package com.clinic.exception;

public class PeselAlreadyInUseException extends Exception{

	private static final long serialVersionUID = 1L;

	public PeselAlreadyInUseException() {
		super("User with this pesel already exists.");
	}

	public PeselAlreadyInUseException(String messsage) {
		super(messsage);
	}
}
