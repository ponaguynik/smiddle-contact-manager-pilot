package ua.com.smiddle.smiddlecontactmanager.dao.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.smiddle.smiddlecontactmanager.dao.type.AppealTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeDao;
import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;

@Repository
public class AppealTypeDaoImpl extends EntityTypeMongoDao<AppealType, Appeal>
        implements EntityTypeDao<AppealType, Appeal>, AppealTypeDao {

    @Autowired
    public AppealTypeDaoImpl(MongoTemplate mongoTemplate) {
        super(AppealType.class, mongoTemplate);
    }
}
