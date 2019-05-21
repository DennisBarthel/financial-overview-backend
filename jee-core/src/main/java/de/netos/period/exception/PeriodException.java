package de.netos.period.exception;

public class PeriodException extends Exception {

	private static final long serialVersionUID = -1307434586876024132L;

	public enum ErrorMessage {
		PERIOD_NOT_FOUND, PERIOD_EXISTS, INVALID_TIME_PERIOD;
	}

	private final ErrorMessage errorMessage;

	public PeriodException(ErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public PeriodException(ErrorMessage errorMessage, String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = errorMessage;
	}

	public PeriodException(ErrorMessage errorMessage, String message) {
		super(message);
		this.errorMessage = errorMessage;
	}

	public PeriodException(ErrorMessage errorMessage, Throwable cause) {
		super(cause);
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
