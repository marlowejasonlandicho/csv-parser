package library.parser.exception;

public class InvalidDataException extends Exception {

	private static final String PREFIX = "InvalidDataException occured. ";

	public InvalidDataException() {
		super(PREFIX);
	}

	public InvalidDataException(String message) {
		super(PREFIX + message);
	}
}
