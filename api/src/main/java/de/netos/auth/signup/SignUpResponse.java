package de.netos.auth.signup;

public class SignUpResponse {

	private boolean success;
	
	public SignUpResponse(boolean success) {
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
