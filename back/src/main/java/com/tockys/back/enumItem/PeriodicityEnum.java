package com.tockys.back.enumItem;

public enum PeriodicityEnum {
	ONCE("O"), EVERYDAY("E"), BY_WEEK("W");
	
    private String periodicity;
    
    private PeriodicityEnum(String periodicity) {
        this.periodicity = periodicity;
    }
 
    public String getPeriodicity() {
        return periodicity;
    }
}