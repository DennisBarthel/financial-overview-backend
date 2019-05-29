package de.netos.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {
	
    private final String message;
    private final String error;

    @JsonCreator
    public ApiError(
    		@JsonProperty("message") String message,
    		@JsonProperty("error") String error) {
        this.message = message;
        this.error = error;
    }
    
    public String getError() {
		return error;
	}
    
    public String getMessage() {
		return message;
	}
}
