package com.app.custom_excs;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(String mesg) {
		super(mesg);
	}
}
