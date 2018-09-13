package ua.com.smiddle.smiddlecontactmanager.dao.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.type.ActionTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;

@Repository
public class ActionTypeDaoImpl extends EntityTypeMongoDao<ActionType, Action>
        implements EntityTypeDao<ActionType, Action>, ActionTypeDao {

    @Autowired
    public ActionTypeDaoImpl(MongoTemplate mongoTemplate) {
        super(ActionType.class, mongoTemplate);
    }
}
