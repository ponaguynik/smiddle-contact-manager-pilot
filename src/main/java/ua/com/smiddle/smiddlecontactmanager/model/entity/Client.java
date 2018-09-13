package ua.com.smiddle.smiddlecontactmanager.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "clients")
public class Client extends Entity<Client, ClientType> {
}
