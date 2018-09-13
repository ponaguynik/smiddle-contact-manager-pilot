package ua.com.smiddle.smiddlecontactmanager.exception;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;

public class EntityNotFoundException extends ResourceNotFound {

    public EntityNotFoundException(String field, Object value) {
        super(Entity.class.getSimpleName(), field, value);
    }
}
