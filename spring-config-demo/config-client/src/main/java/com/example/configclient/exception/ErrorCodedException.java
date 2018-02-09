package com.example.configclient.exception;

/**
 *Create by yangwenfu on 2018/1/30
 */
public abstract class ErrorCodedException extends RuntimeException implements ErrorCoded{
	private String errorCode;

	public ErrorCodedException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCodedException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
