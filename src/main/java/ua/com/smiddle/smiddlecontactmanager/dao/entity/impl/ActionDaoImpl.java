package ua.com.smiddle.smiddlecontactmanager.dao.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ActionDao;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;

import java.util.List;

@Repository
public class ActionDaoImpl extends EntityMongoDao<Action, ActionType> implements ActionDao {

    @Autowired
    public ActionDaoImpl(MongoTemplate mongoTemplate) {
        super(Action.class, mongoTemplate);
    }

    @Override
    public List<Action> findAllByAppealId(String appealId) {
        return mongoTemplate.find(Query.query(Criteria.where("appeal.id").is(appealId)), entityClass);
    }
}
