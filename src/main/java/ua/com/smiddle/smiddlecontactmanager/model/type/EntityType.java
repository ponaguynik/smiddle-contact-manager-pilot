package ua.com.smiddle.smiddlecontactmanager.model.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The point of such complicated generic typing is that
 * EntityType must have corresponding {@link Entity} of this EntityType and vice versa.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
public abstract class EntityType<T extends EntityType<T, E>, E extends Entity<E, T>> implements Cloneable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime lastModifiedDate;
    private List<Field> fixedFields = new ArrayList<>();
    private List<Field> customFields = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    public T clone() {
        T  clone;
        try {
            clone = (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning error", e);
        }
        clone.setFixedFields(fixedFields.stream().map(Field::clone).collect(Collectors.toList()));
        clone.setCustomFields(customFields.stream().map(Field::clone).collect(Collectors.toList()));
        return clone;
    }

    @JsonIgnore
    public List<Field> getAllFields() {
        List<Field> all = new ArrayList<>(fixedFields);
        all.addAll(customFields);
        return all;
    }
}
