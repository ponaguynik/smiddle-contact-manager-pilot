package ua.com.smiddle.smiddlecontactmanager.exception;

import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

public class EntityTypeNotFoundException extends ResourceNotFound {

    public EntityTypeNotFoundException(String field, Object value) {
        super(EntityType.class.getSimpleName(), field, value);
    }
}
