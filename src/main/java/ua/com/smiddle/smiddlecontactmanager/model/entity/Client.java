package ua.com.smiddle.smiddlecontactmanager.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;


@CompoundIndexes({
        @CompoundIndex(name = "client_to_type", def = "{'type.id' : 1}"),
        @CompoundIndex(name = "attributes_fixed", def = "{'attributes.fixed' : 1}")
})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "clients")
public class Client extends Entity<Client, ClientType> {
}
