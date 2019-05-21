package de.netos.base.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.netos.account.exception.AccountException;
import de.netos.auth.exception.LoginException;
import de.netos.period.exception.PeriodException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({
		AccountException.class,
		PeriodException.class,
		LoginException.class
	})
	public final ResponseEntity<ApiError> handleCustomException(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		
		if (exception instanceof AccountException) {
			ApiError apiError = handleAccountException((AccountException) exception);
			
			return new ResponseEntity<>(apiError, apiError.getStatus());
		} else if (exception instanceof PeriodException) {
			ApiError apiError = handlePeriodException((PeriodException) exception);
			
			return new ResponseEntity<>(apiError, apiError.getStatus());
		} else if (exception instanceof LoginException) {
			ApiError apiError = handleLoginException((LoginException) exception);
			
			return new ResponseEntity<>(apiError, apiError.getStatus());
		} else {
			return handleEverythingElse(exception, request);
		}
	}
	
	private ApiError handleAccountException(AccountException exception) {
		return new ApiError(HttpStatus.BAD_REQUEST, exception.getErrorMessage().name(), exception.getMessage());
	}
	
	private ApiError handlePeriodException(PeriodException exception) {
		return new ApiError(HttpStatus.BAD_REQUEST, exception.getErrorMessage().name(), exception.getMessage());
	}
	
	private ApiError handleLoginException(LoginException exception) {
		return new ApiError(HttpStatus.BAD_REQUEST, exception.getErrorMessage().name(), exception.getMessage());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	     
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
	     
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApiError> handleEverythingElse(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		
		final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), "error occurred");
		
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
