package ua.com.smiddle.smiddlecontactmanager.model.type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "actionTypes")
public class ActionType extends EntityType<ActionType, Action> {
}
