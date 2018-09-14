package ua.com.smiddle.smiddlecontactmanager.validator;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;

@Component
public class EntityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Entity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Entity<?, ?> entity = (Entity<?, ?>) target;
        if (Strings.isNullOrEmpty(entity.getId())) {
            errors.reject("id.null", "Id is not present");
        }
        if (entity.getType() == null) {
            errors.reject("type.null", "Type is not present");
        } else if (Strings.isNullOrEmpty(entity.getType().getId())) {
            errors.reject("type.id.null", "Type id is not present");
        }
    }
}
