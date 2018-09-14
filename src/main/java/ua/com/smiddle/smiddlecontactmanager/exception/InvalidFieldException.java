package ua.com.smiddle.smiddlecontactmanager.exception;

import java.util.Collection;
import java.util.stream.Collectors;

public class InvalidFieldException extends BadRequestException {

    public InvalidFieldException(Collection<String> fieldNames) {
        super("Fields are invalid: " + fieldNames.stream().collect(Collectors.joining(", ")));
    }
}
