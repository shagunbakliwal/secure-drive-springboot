package com.ssp.storage.exception;

import com.ssp.storage.constant.ErrorCode;

public class FileException extends Exception {

	private ErrorCode error;
	private int errorCode;
	private String message;

	public FileException(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public FileException(ErrorCode error) {
		this.errorCode = error.getKey();
		this.message = error.getValue();
	}

}
