package ua.com.smiddle.smiddlecontactmanager.dao.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.AppealDao;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;

import java.util.List;

@Repository
public class AppealDaoImpl extends EntityMongoDao<Appeal, AppealType> implements AppealDao {

    @Autowired
    public AppealDaoImpl(MongoTemplate mongoTemplate) {
        super(Appeal.class, mongoTemplate);
    }

    @Override
    public List<Appeal> findAllByClientId(String clientId) {
        return mongoTemplate.find(Query.query(Criteria.where("client.id").is(clientId)), entityClass);
    }
}
