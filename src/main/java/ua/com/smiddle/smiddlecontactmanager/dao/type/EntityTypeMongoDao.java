package ua.com.smiddle.smiddlecontactmanager.dao.type;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ua.com.smiddle.smiddlecontactmanager.dao.AbstractMongoDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.Optional;

public abstract class EntityTypeMongoDao<T extends EntityType<T, E>, E extends Entity<E, T>>
        extends AbstractMongoDao<T, String> implements EntityTypeDao<T, E> {

    protected EntityTypeMongoDao(Class<T> typeClass, MongoTemplate mongoTemplate) {
        super(typeClass, mongoTemplate);
    }

    @Override
    public Optional<T> findByName(String name) {
        T typeByName = mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), entityClass);
        return Optional.ofNullable(typeByName);
    }
}
