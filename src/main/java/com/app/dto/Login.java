package com.app.dto;

public class Login {
	private String personEmail;
	private String password;
	public Login(String personEmail, String password) {
		super();
		this.personEmail = personEmail;
		this.password = password;
	}
	public Login() {
		super();
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}