package com.tockys.back.dto;

public class UserPasswordDTO {

	private String oldPassword;
	private String newPassword;
	
	public String getOldPassword() {
		return this.oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return this.newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}