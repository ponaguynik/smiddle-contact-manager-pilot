package ua.com.smiddle.smiddlecontactmanager.validator;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;

@Component
public class ActionValidator implements Validator {
    private final EntityValidator entityValidator;

    @Autowired
    public ActionValidator(EntityValidator entityValidator) {
        this.entityValidator = entityValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Action.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Action action = (Action) target;
        ValidationUtils.invokeValidator(entityValidator, target, errors);
        if (action.getAppeal() == null) {
            errors.reject("appeal.null", "Appeal is not present");
        } else if (Strings.isNullOrEmpty(action.getAppeal().getId())) {
            errors.reject("appeal.id.null", "Appeal id is not present");
        }
    }
}
