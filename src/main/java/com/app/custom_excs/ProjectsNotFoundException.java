package com.app.custom_excs;

@SuppressWarnings("serial")
public class ProjectsNotFoundException extends RuntimeException {
	public ProjectsNotFoundException(String mesg) {
		super(mesg);
	}
}
