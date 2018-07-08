package com.ssp.storage.web;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ssp.storage.error.AcgError;

@JsonInclude(value = Include.NON_NULL)
public class ResponseEntity<T> {
	private T body;
	private AcgError error;
	private HttpStatus status;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public AcgError getError() {
		return error;
	}

	public void setError(AcgError error) {
		this.error = error;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ResponseEntity(T body, HttpStatus status) {
		super();
		this.body = body;
		this.status = status;
	}
	
	public ResponseEntity(AcgError error, HttpStatus status) {
		super();
		this.error = error;
		this.status = status;
	}

}
