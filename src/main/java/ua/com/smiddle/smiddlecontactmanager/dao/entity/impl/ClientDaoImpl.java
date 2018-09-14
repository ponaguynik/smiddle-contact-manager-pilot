package ua.com.smiddle.smiddlecontactmanager.dao.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ClientDao;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;

import java.util.List;

@Repository
public class ClientDaoImpl extends EntityMongoDao<Client, ClientType> implements ClientDao {

    @Autowired
    public ClientDaoImpl(MongoTemplate mongoTemplate) {
        super(Client.class, mongoTemplate);
    }

    @Override
    public List<Client> findByFixed(String fixed) {
        return mongoTemplate.find(Query.query(Criteria.where("attributes.fixed").is(fixed)), entityClass);
    }
}
