package com.ssp.storage.constant;

public enum ErrorCode {
	EMAIL_DUPLICATE(1, "Email is already registered"), USERNAME_DUPLICATE(2,
			"Username is already registered"), INVALID_CREDENTIALS(3, "Invalid Login Credentials"), DOWNLOAD_FAILED(4,
					"Download Failed"), INVALID_FILE(5, "Invalid File");

	private int key;
	private String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private ErrorCode(int key, String value) {
		this.key = key;
		this.value = value;
	}

}
