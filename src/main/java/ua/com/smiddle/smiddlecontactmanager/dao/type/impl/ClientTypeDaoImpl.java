package ua.com.smiddle.smiddlecontactmanager.dao.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.type.ClientTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;

@Repository
public class ClientTypeDaoImpl extends EntityTypeMongoDao<ClientType, Client>
        implements EntityTypeDao<ClientType, Client>, ClientTypeDao {

    @Autowired
    public ClientTypeDaoImpl(MongoTemplate mongoTemplate) {
        super(ClientType.class, mongoTemplate);
    }
}
