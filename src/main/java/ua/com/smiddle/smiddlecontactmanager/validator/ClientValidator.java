package ua.com.smiddle.smiddlecontactmanager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;

@Component
public class ClientValidator implements Validator {
    private final EntityValidator entityValidator;

    @Autowired
    public ClientValidator(EntityValidator entityValidator) {
        this.entityValidator = entityValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.invokeValidator(entityValidator, target, errors);
    }
}
