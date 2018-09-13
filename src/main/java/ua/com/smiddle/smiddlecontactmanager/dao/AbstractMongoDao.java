package ua.com.smiddle.smiddlecontactmanager.dao;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMongoDao<E, ID> implements GenericDao<E, ID> {
    protected final Class<E> entityClass;
    protected final MongoTemplate mongoTemplate;

    protected AbstractMongoDao(Class<E> entityClass, MongoTemplate mongoTemplate) {
        this.entityClass = entityClass;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public E save(E type) {
        mongoTemplate.save(type);
        return type;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(mongoTemplate.findById(id, entityClass));
    }

    @Override
    public List<E> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public void delete(E e) {
        mongoTemplate.remove(e);
    }
}
