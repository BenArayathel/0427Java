package com.bankofben.business;

public class BusinessException extends Exception {

	/**
	 * BusinessException is thrown when an anticipated error related to improper inputs or database problems is detected.
	 * Business exceptions must always have a String message argument that the PresentationLayer will use to inform the user.
	 */
	private static final long serialVersionUID = 4752693343179594139L;

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

}
