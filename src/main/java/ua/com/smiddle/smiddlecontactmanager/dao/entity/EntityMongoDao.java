package ua.com.smiddle.smiddlecontactmanager.dao.entity;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ua.com.smiddle.smiddlecontactmanager.dao.AbstractMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.List;

public abstract class EntityMongoDao<E extends Entity<E, T>, T extends EntityType<T, E>>
        extends AbstractMongoDao<E, String> implements EntityDao<E, T> {

    protected EntityMongoDao(Class<E> entityClass, MongoTemplate mongoTemplate) {
        super(entityClass, mongoTemplate);
    }

    @Override
    public List<E> findAllByTypeId(String typeId) {
        return mongoTemplate.find(Query.query(Criteria.where("type.id").is(typeId)), entityClass);
    }
}
