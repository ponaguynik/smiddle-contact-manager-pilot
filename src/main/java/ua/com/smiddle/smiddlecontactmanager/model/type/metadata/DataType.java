package ua.com.smiddle.smiddlecontactmanager.model.type.metadata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Strings;
import ua.com.smiddle.smiddlecontactmanager.exception.InvalidEnumNameException;

public enum DataType {
    STRING, INTEGER;

    @JsonCreator
    public static DataType forName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }
        for (DataType inputType : values()) {
            if (inputType.name().equalsIgnoreCase(name)) {
                return inputType;
            }
        }
        throw new InvalidEnumNameException(name, DataType.class.getSimpleName());
    }
}
