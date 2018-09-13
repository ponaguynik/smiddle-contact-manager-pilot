package ua.com.smiddle.smiddlecontactmanager.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.Field;
import ua.com.smiddle.smiddlecontactmanager.template.EntityTypeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientTypeValidator implements Validator {
    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([a-z]+[A-Z]+\\\\w+)+");
    private final ClientType template = EntityTypeTemplate.CLIENT_TYPE.getTemplate();

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientType.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClientType clientType = (ClientType) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, clientType.getName(), "name.empty", "Name is empty");
        validateFields(errors, clientType);
    }

    private void validateFields(Errors errors, ClientType type) {
        if (type.getCustomFields() != null && !type.getCustomFields().isEmpty()) {
            List<String> fixedFieldNames = template.getFixedFields().stream()
                    .map(Field::getName).collect(Collectors.toList());
            List<String> customFieldNames = type.getCustomFields().stream()
                    .map(Field::getName).collect(Collectors.toList());
            List<String> duplicates = new ArrayList<>(fixedFieldNames);
            duplicates.retainAll(customFieldNames);
            if (!duplicates.isEmpty()) {
                String[] duplicateNames = duplicates.toArray(new String[0]);
                errors.reject("name.duplicate", duplicateNames, "Names of fields are duplicated: " + duplicates.stream().reduce((s, s2) -> s + ", " + s2));
            }
            List<String> invalidNames = Stream.concat(fixedFieldNames.stream(), customFieldNames.stream())
                    .filter(name -> !CAMEL_CASE_PATTERN.matcher(name).matches())
                    .collect(Collectors.toList());
            if (!invalidNames.isEmpty()) {
                String[] namesArray = invalidNames.toArray(new String[0]);
                errors.reject("name.invalid", namesArray, "Names of fields are not in camel case: " + invalidNames.stream().reduce((s, s2) -> s + ", " + s2));
            }
        }
    }
}
