package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityDao;
import ua.com.smiddle.smiddlecontactmanager.exception.EntityNotFoundException;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;
import ua.com.smiddle.smiddlecontactmanager.service.type.EntityTypeService;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractEntityService<E extends Entity<E, T>, T extends EntityType<T, E>>
        implements EntityService<E, T> {
    private final EntityDao<E, T> entityDao;
    private final EntityTypeService<T, E> typeService;

    public AbstractEntityService(EntityDao<E, T> entityDao, EntityTypeService<T, E> typeService) {
        this.entityDao = entityDao;
        this.typeService = typeService;
    }

    @Override
    public List<E> findAll() {
        return entityDao.findAll();
    }

    @Override
    public List<E> findAllByTypeId(String typeId) {
        return entityDao.findAllByTypeId(typeId);
    }

    @Override
    public E findById(String id) {
        return entityDao.findById(id).orElseThrow(() -> new EntityNotFoundException("id", id));
    }

    @Override
    public E create(E entity) {
        validateOnCreation(entity);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastModifiedDate(entity.getCreatedDate());
        return entityDao.save(entity);
    }

    private void validateOnCreation(E entity) {
        T type = typeService.findById(entity.getType().getId());
        entity.setType(type);
        validateFields(entity);
    }

    private void validateFields(E entity) {

    }

    @Override
    public E update(E entity) {
        // check if such type exists
        findById(entity.getId());
        entity.setLastModifiedDate(LocalDateTime.now());
        return entityDao.save(entity);
    }

    @Override
    public E deleteById(String id) {
        E entity = findById(id);
        // TODO: 06-Sep-18 check constraints
        entityDao.delete(entity);
        return entity;
    }
}
