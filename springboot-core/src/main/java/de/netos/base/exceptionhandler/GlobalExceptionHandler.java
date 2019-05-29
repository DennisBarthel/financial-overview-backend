package de.netos.base.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.netos.account.exception.AccountException;
import de.netos.auth.exception.LoginException;
import de.netos.error.ApiError;
import de.netos.period.exception.PeriodException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({
		AccountException.class,
		PeriodException.class,
		LoginException.class,
		AuthenticationException.class
	})
	public final ResponseEntity<ApiError> handleCustomException(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		
		if (exception instanceof AccountException) {
			return handleAccountException((AccountException) exception);
		} else if (exception instanceof PeriodException) {
			return handlePeriodException((PeriodException) exception);
		} else if (exception instanceof LoginException) {
			return handleLoginException((LoginException) exception);
		} else if (exception instanceof AuthenticationException) {
			return handleAuthenticationException((AuthenticationException) exception);
		} else {
			return handleEverythingElse(exception, request);
		}
	}
	
	private ResponseEntity<ApiError> handleAccountException(AccountException exception) {
		ApiError apiError = new ApiError(exception.getErrorMessage().name(), exception.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<ApiError> handlePeriodException(PeriodException exception) {
		ApiError apiError = new ApiError(exception.getErrorMessage().name(), exception.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<ApiError> handleLoginException(LoginException exception) {
		ApiError apiError = new ApiError(exception.getErrorMessage().name(), exception.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception) {
		ApiError apiError = new ApiError(exception.getMessage(), exception.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApiError> handleEverythingElse(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		
		final ApiError apiError = new ApiError(exception.getLocalizedMessage(), "error occurred");
		
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
