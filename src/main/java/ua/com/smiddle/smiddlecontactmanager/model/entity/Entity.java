package ua.com.smiddle.smiddlecontactmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The point of such complicated generic typing is that
 * Entity must have corresponding {@link EntityType} of this Entity and vice versa.
 */
@CompoundIndexes({
        @CompoundIndex(name = "entity_to_type", def = "{'type.id' : 1}")
})
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Entity<E extends Entity<E, T>, T extends EntityType<T, E>> {
    @Id
    protected String id;
    @DBRef
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
    public Map<String,String> getMap() {
        return attributes;
    }
}
