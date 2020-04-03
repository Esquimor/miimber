package com.tockys.back.model.enums;

public enum UserRoleEnum {
	Admin("A"), User("U");
	
    private String role;
    
    private UserRoleEnum(String role) {
        this.role = role;
    }
 
    public String getRole() {
        return role;
    }
}
