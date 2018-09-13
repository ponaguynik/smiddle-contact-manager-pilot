package ua.com.smiddle.smiddlecontactmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEnumNameException extends RuntimeException {
    private static final String MESSAGE_FORMAT = "Invalid enum name='%s' for enum %s";

    public InvalidEnumNameException(String message) {
        super(message);
    }

    public InvalidEnumNameException(String value, String enumName) {
        super(String.format(MESSAGE_FORMAT, value, enumName));
    }
}
