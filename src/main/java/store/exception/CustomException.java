package store.exception;

public class CustomException extends IllegalArgumentException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public CustomException(ExceptionMessage exceptionMessage) {
        super(ERROR_PREFIX + exceptionMessage.getMessage());
    }
}
