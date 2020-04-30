package exceptions;

public class InvalidAgeException extends Exception {

    // Creating a specific custom exception

    /**
     *  Don't worry about serialization yet; just know it's required when creating custom exceptions.
     */
    private static final long serialVersionUID = 1L;

    public InvalidAgeException() {
        super();
    }

    public InvalidAgeException(final String message) {
        super(message);
    }
    
}