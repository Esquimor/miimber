package com.miimber.back.core.enums;

public enum LangEnum {
	FR("fr"), EN("EN");
	
    private String lang;
    
    private LangEnum(String lang) {
        this.lang = lang;
    }
 
    public String getLang() {
        return lang;
    }
}
