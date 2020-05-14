package exception;

/*
	Handles all exceptions.
 */
public class BusinessException extends Exception {

	public BusinessException() {
	}

	public BusinessException(final String message) {
		super(message);
	}
}
