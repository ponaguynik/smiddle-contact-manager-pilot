package ua.com.smiddle.smiddlecontactmanager.validator;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;

@Component
public class AppealValidator implements Validator {
    private final EntityValidator entityValidator;

    @Autowired
    public AppealValidator(EntityValidator entityValidator) {
        this.entityValidator = entityValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Appeal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appeal appeal = (Appeal) target;
        ValidationUtils.invokeValidator(entityValidator, target, errors);
        if (appeal.getClient() == null) {
            errors.reject("client.null", "Client is not present");
        } else if (Strings.isNullOrEmpty(appeal.getClient().getId())) {
            errors.reject("client.id.null", "Client id is not present");
        }
    }
}
