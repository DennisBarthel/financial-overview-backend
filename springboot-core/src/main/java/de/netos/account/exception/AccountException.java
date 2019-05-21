package de.netos.account.exception;

public class AccountException extends Exception {

	public enum ErrorMessage {
		ACCOUNT_NOT_FOUND,
		ACCOUNT_EXISTS,
		INVALID_ACCOUNTID;
	}

	private ErrorMessage errorMessage;

	public AccountException(ErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public AccountException(ErrorMessage errorMessage, String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = errorMessage;
	}

	public AccountException(ErrorMessage errorMessage, String message) {
		super(message);
		this.errorMessage = errorMessage;
	}

	public AccountException(ErrorMessage errorMessage, Throwable cause) {
		super(cause);
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
