package ua.com.smiddle.smiddlecontactmanager.service.type;

import ua.com.smiddle.smiddlecontactmanager.dao.type.EntityTypeDao;
import ua.com.smiddle.smiddlecontactmanager.exception.EntityTypeNotFoundException;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractEntityTypeService<T extends EntityType<T, E>, E extends Entity<E, T>>
        implements EntityTypeService<T, E> {
    private final EntityTypeDao<T, E> entityTypeDao;

    protected AbstractEntityTypeService(EntityTypeDao<T, E> entityTypeDao) {
        this.entityTypeDao = entityTypeDao;
    }

    @Override
    public List<T> findAll() {
        return entityTypeDao.findAll();
    }

    @Override
    public T findById(String id) {
        return entityTypeDao.findById(id).orElseThrow(() -> new EntityTypeNotFoundException("id", id));
    }

    @Override
    public T create(T type) {
        // auto-generate ids
        type.setId(null);
        type.setCreatedDate(LocalDateTime.now());
        type.setLastModifiedDate(type.getCreatedDate());
        return entityTypeDao.save(type);
    }

    @Override
    public T update(T type) {
        findById(type.getId());
        type.setLastModifiedDate(LocalDateTime.now());
        return entityTypeDao.save(type);
    }

    @Override
    public T deleteById(String id) {
        T type = findById(id);
        // TODO: 06-Sep-18 check constraints
        entityTypeDao.delete(type);
        return type;
    }
}
