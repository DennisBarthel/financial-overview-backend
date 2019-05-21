package de.netos.base.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private final HttpStatus status;
    private final String message;
    private final List<String> errors;
 
    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
    
    public HttpStatus getStatus() {
		return status;
	}
    
    public List<String> getErrors() {
		return errors;
	}
    
    public String getMessage() {
		return message;
	}
}
