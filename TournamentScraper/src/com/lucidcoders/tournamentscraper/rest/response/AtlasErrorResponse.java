package com.lucidcoders.tournamentscraper.rest.response;

public class AtlasErrorResponse {
    
    private String errorType;
    private String error;
    
    public String getErrorType() {
	return errorType;
    }
    
    public void setErrorType(String errorType) {
	this.errorType = errorType;
    }
    
    public String getError() {
	return error;
    }
    
    public void setError(String error) {
	this.error = error;
    }
}
