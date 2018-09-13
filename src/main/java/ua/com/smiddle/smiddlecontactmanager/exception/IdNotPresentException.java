package ua.com.smiddle.smiddlecontactmanager.exception;

public class IdNotPresentException extends BadRequestException {

    public IdNotPresentException(String name) {
        super(String.format("%s id is not present", name));
    }
}
