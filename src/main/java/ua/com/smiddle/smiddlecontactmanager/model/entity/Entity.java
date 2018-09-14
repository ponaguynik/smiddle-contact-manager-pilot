package ua.com.smiddle.smiddlecontactmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The point of such complicated generic typing is that
 * Entity must have corresponding {@link EntityType} of this Entity and vice versa.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
public abstract class Entity<E extends Entity<E, T>, T extends EntityType<T, E>> {
    @Id
    protected String id;
    @DBRef
    @JsonIgnoreProperties({"fixedFields", "customFields"})
    protected T type;
    @JsonIgnore
    protected LocalDateTime createdDate;
    @JsonIgnore
    protected LocalDateTime lastModifiedDate;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected Map<String, String> attributes = new HashMap<>();

    @JsonAnySetter
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @JsonAnyGetter
    public Map<String,String> getAttributes() {
        return attributes;
    }
}
