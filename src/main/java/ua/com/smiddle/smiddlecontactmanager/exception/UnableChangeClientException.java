package ua.com.smiddle.smiddlecontactmanager.exception;

public class UnableChangeClientException extends BadRequestException {

    public UnableChangeClientException() {
        super("Changing client is not allowed");
    }
}
