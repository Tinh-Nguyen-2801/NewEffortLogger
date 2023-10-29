package model;

public class StatusMessage {
	private boolean valid;
	private String message;

	public StatusMessage() {
		valid = false;
		message = "";
	}

	public StatusMessage(boolean _valid, String _message) {
		valid = _valid;
		message = _message;
	}

	public boolean isValid() {
		return valid;
	}

	public void setStatus(boolean _valid) {
		this.valid = _valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
