package com.app.custom_excs;

@SuppressWarnings("serial")
public class IssuesNotFoundException extends RuntimeException {
	public IssuesNotFoundException(String mesg) {
		super(mesg);
	}
}
