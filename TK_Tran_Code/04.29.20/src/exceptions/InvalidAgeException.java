package exceptions;

public class InvalidAgeException extends Exception {

    // Creating a specific custom exception

    /**
     *
     */
    private static final long serialVersionUID = 1671407469862967752L;

    public InvalidAgeException() {
        super();
    }

    public InvalidAgeException(final String message) {
        super(message);
    }
    
}