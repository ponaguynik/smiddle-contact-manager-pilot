package ua.com.smiddle.smiddlecontactmanager.model.type.metadata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Strings;
import ua.com.smiddle.smiddlecontactmanager.exception.InvalidEnumNameException;

public enum InputType {
    INPUT, SELECT;

    @JsonCreator
    public static InputType forName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }
        for (InputType inputType : values()) {
            if (inputType.name().equalsIgnoreCase(name)) {
                return inputType;
            }
        }
        throw new InvalidEnumNameException(name, InputType.class.getSimpleName());
    }
}
