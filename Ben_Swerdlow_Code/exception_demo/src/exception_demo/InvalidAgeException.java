package exception_demo;

public class InvalidAgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAgeException() {
		super();
	}
	
	public InvalidAgeException(final String s) {
		super(s);
	}
}
