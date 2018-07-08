package com.ssp.storage.error;

import org.springframework.http.HttpStatus;

public class AcgError {

	private final int errorCode;
	private final String message;

	public int getErrorCode() {
		return errorCode;
	}

	public AcgError() {
		super();
		this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.message = "Please try after sometime.";
	}

	public AcgError(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
