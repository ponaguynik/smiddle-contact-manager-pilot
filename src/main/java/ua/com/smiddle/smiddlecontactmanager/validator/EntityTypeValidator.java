package ua.com.smiddle.smiddlecontactmanager.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.Field;
import ua.com.smiddle.smiddlecontactmanager.template.EntityTypeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EntityTypeValidator implements Validator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{1,12}$");
    private final ClientType template = EntityTypeTemplate.CLIENT_TYPE.getTemplate();

    @Override
    public boolean supports(Class<?> clazz) {
        return EntityType.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EntityType<?, ?> type = (EntityType<?, ?>) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name is empty");
        validateFields(errors, type);
    }

    private void validateFields(Errors errors, EntityType<?, ?> type) {
        if (!Objects.equals(template.getFixedFields(), type.getFixedFields())) {
            errors.reject("template.not.match", "Type doesn't match its template. Please create new type from template.");
        }
        if (type.getCustomFields() != null && !type.getCustomFields().isEmpty()) {
            List<String> fixedFieldNames = template.getFixedFields().stream()
                    .map(Field::getName).collect(Collectors.toList());
            List<String> customFieldNames = type.getCustomFields().stream()
                    .map(Field::getName).collect(Collectors.toList());
            List<String> duplicates = new ArrayList<>(fixedFieldNames);
            duplicates.retainAll(customFieldNames);
            if (!duplicates.isEmpty()) {
                String[] duplicateNames = duplicates.toArray(new String[0]);
                errors.reject("name.duplicate", duplicateNames, "Names of fields are duplicated: " + duplicates.stream().collect(Collectors.joining(", ")));
            }
            List<String> invalidNames = Stream.concat(fixedFieldNames.stream(), customFieldNames.stream())
                    .filter(name -> !NAME_PATTERN.matcher(name).matches())
                    .collect(Collectors.toList());
            if (!invalidNames.isEmpty()) {
                String[] namesArray = invalidNames.toArray(new String[0]);
                errors.reject("name.invalid", namesArray, "The names of fields must consist of English letters, at least 1 and not more than 12 characters length: " + duplicates.stream().collect(Collectors.joining(", ")));
            }
        }
    }
}
