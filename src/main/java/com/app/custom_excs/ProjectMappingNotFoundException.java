package com.app.custom_excs;

@SuppressWarnings("serial")
public class ProjectMappingNotFoundException extends RuntimeException {
	public ProjectMappingNotFoundException(String mesg) {
		super(mesg);
	}
}
