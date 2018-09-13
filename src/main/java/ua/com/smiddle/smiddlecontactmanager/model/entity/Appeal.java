package ua.com.smiddle.smiddlecontactmanager.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;

@CompoundIndexes({
        @CompoundIndex(name = "appeal_to_client", def = "{'client.id' : 1}")
})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "appeals")
public class Appeal extends Entity<Appeal, AppealType> {
    @DBRef
    private Client client;
}
