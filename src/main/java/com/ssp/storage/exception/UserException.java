package com.ssp.storage.exception;

import org.springframework.http.HttpStatus;

import com.ssp.storage.constant.ErrorCode;

public class UserException extends Exception {
	private static final long serialVersionUID = -6770286498023832948L;
	private int errorCode;
	private String message;

	public UserException(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public UserException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		this.message = message;
		this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserException(ErrorCode errorCode) {
		this.message = errorCode.getValue();
		this.errorCode = errorCode.getKey();
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
