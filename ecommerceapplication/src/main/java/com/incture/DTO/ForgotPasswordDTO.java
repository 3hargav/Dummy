package com.incture.DTO;

public class ForgotPasswordDTO {

	private String password;
	private String confirm_password;
	private String email_id;
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String forgot_password) {
		this.confirm_password = forgot_password;
	}
	
}
