package com.acss.poc.core;

public enum ValidationResult {
	SUCCESS("true"),
    FAILED("false");
    private final String status;

    private ValidationResult(String status) {
       this.status = status;
    }

    public String getValue() {
       return status;
    }
}
