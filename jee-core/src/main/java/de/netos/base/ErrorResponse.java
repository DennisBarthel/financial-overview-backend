package de.netos.base;

public class ErrorResponse {

	private String errorMessageKey;
	private String errorMessageValue;

	public ErrorResponse() {
	}

	public ErrorResponse(String errorMessageKey, String errorMessageValue) {
		this.errorMessageKey = errorMessageKey;
		this.errorMessageValue = errorMessageValue;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageValue() {
		return errorMessageValue;
	}

	public void setErrorMessageValue(String errorMessageValue) {
		this.errorMessageValue = errorMessageValue;
	}
}
