package ua.com.smiddle.smiddlecontactmanager.exception;

public class UnableChangeTypeException extends BadRequestException {

    public UnableChangeTypeException() {
        super("Changing type of the entity is not allowed.");
    }

    public UnableChangeTypeException(String message) {
        super(message);
    }
}
