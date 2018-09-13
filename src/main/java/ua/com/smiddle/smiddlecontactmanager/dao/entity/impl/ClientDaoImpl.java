package ua.com.smiddle.smiddlecontactmanager.dao.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ClientDao;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;

@Repository
public class ClientDaoImpl extends EntityMongoDao<Client, ClientType> implements ClientDao {

    @Autowired
    public ClientDaoImpl(MongoTemplate mongoTemplate) {
        super(Client.class, mongoTemplate);
    }
}
