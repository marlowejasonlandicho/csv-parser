package library.parser.exception;

/**
 * Generic exception for raising errors
 * 
 * @author marlowelandicho
 *
 */
public class InvalidDataException extends RuntimeException {

	private static final String PREFIX = "InvalidDataException occured. ";

	public InvalidDataException() {
		super(PREFIX);
	}

	public InvalidDataException(String message) {
		super(PREFIX + message);
	}
}
