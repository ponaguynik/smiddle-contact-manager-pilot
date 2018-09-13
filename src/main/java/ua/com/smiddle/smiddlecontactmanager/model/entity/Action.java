package ua.com.smiddle.smiddlecontactmanager.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;

@CompoundIndexes({
        @CompoundIndex(name = "action_to_appeal", def = "{'appeal.id' : 1}")
})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "actions")
public class Action extends Entity<Action, ActionType> {
    @DBRef
    private Appeal appeal;
}
