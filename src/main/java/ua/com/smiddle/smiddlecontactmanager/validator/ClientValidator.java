package ua.com.smiddle.smiddlecontactmanager.validator;

import com.google.common.base.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;

public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if (Strings.isNullOrEmpty(client.getId())) {
            errors.reject("id.null", "Id is null");
        }
        if (client.getType() == null) {
            errors.reject("type.null", "Type is null");
        } else if (Strings.isNullOrEmpty(client.getType().getId())) {
            errors.reject("type.id.null", "Type id is not present");
        }
    }
}
