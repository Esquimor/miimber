package com.tockys.back.model.enums;

public enum RoleEnum {
	OWNER("O"), INSTRUCTOR("I"), MEMBER("M");
	
    private String role;
    
    private RoleEnum(String role) {
        this.role = role;
    }
 
    public String getRole() {
        return role;
    }
}