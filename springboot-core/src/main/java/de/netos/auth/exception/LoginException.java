package de.netos.auth.exception;

public class LoginException extends Exception {

	public enum ErrorMessage {
		EMAIL_ALREADY_EXISTS
	}
	
	private ErrorMessage errorMessage;

	public LoginException(ErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public LoginException(ErrorMessage errorMessage, String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = errorMessage;
	}

	public LoginException(ErrorMessage errorMessage, String message) {
		super(message);
		this.errorMessage = errorMessage;
	}

	public LoginException(ErrorMessage errorMessage, Throwable cause) {
		super(cause);
		this.errorMessage = errorMessage;
	}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
