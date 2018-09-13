package ua.com.smiddle.smiddlecontactmanager.template;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.DataType;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.Field;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.InputType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum EntityTypeTemplate {
    CLIENT_TYPE(
            ClientType::new,
            Field.builder().name("fixed").title("Fixed field").importName("fixed")
                    .dataType(DataType.STRING).inputType(InputType.INPUT).length(12).searchable(true).build(),
            Field.builder().name("anotherFixed").title("Another fixed").importName("anotherFixed")
                    .dataType(DataType.INTEGER).inputType(InputType.SELECT).length(4).searchable(true).build()
    ),
    APPEAL_TYPE(
            AppealType::new,
            Field.builder().name("fixed").title("Fixed field").importName("fixed")
                    .dataType(DataType.STRING).inputType(InputType.INPUT).length(12).searchable(true).build(),
            Field.builder().name("anotherFixed").title("Another fixed").importName("anotherFixed")
                    .dataType(DataType.INTEGER).inputType(InputType.SELECT).length(4).searchable(true).build()
    ),
    ACTION_TYPE(
            ActionType::new,
            Field.builder().name("fixed").title("Fixed field").importName("fixed")
                    .dataType(DataType.STRING).inputType(InputType.INPUT).length(12).searchable(true).build(),
            Field.builder().name("anotherFixed").title("Another fixed").importName("anotherFixed")
                    .dataType(DataType.INTEGER).inputType(InputType.SELECT).length(4).searchable(true).build()
    );

    private final List<Field> fixedFields = new ArrayList<>();
    private final Supplier<? extends EntityType<?, ?>> typeSupplier;
    private final EntityType<?, ?> template;

    EntityTypeTemplate(Supplier<? extends EntityType<?, ?>> typeSupplier) {
        this.typeSupplier = typeSupplier;
        this.template = createTemplate();
    }

    EntityTypeTemplate(Supplier<? extends EntityType<?, ?>> typeSupplier, Field... fields) {
        this.typeSupplier = typeSupplier;
        fixedFields.addAll(Arrays.asList(fields));
        this.template = createTemplate();
    }

    @SuppressWarnings("unchecked")
    public <T extends EntityType<T, E>, E extends Entity<E, T>> T getTemplate() {
        return (T) template.clone();
    }

    private EntityType<?, ?> createTemplate() {
        EntityType<?, ?> template = typeSupplier.get();
        template.getFixedFields().addAll(this.fixedFields);
        return template;
    }
}
