package com.github.springlink.basic.core;

import lombok.Getter;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Getter
	private final String code;

	public ServiceException(String code) {
		this.code = code;
	}

	public ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}
}
