package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.dao.entity.EntityDao;
import ua.com.smiddle.smiddlecontactmanager.exception.EntityNotFoundException;
import ua.com.smiddle.smiddlecontactmanager.exception.InvalidFieldException;
import ua.com.smiddle.smiddlecontactmanager.exception.UnableChangeTypeException;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;
import ua.com.smiddle.smiddlecontactmanager.model.type.metadata.Field;
import ua.com.smiddle.smiddlecontactmanager.service.type.EntityTypeService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * This method doesn't save entity to DB so it's possible
     * to process it further in subclasses before saving
     *
     * @return general processed (set type from DB, set created/modified dates, validate fields) entity
     */
    @Override
    public E create(E entity) {
        T type = typeService.findById(entity.getType().getId());
        entity.setType(type);
        validateFields(entity);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastModifiedDate(entity.getCreatedDate());
        return entity;
    }

    /**
     * This method doesn't save entity to DB so it's possible
     * to process it further in subclasses before saving
     *
     * @return general processed (set type from DB, set modified date, validate fields) entity
     */
    @Override
    public E update(E entity) {
        E dbEntity = findById(entity.getId());
        if (!Objects.equals(entity.getType().getId(), dbEntity.getType().getId())) {
            throw new UnableChangeTypeException();
        }
        entity.setType(dbEntity.getType());
        validateFields(entity);
        entity.setLastModifiedDate(LocalDateTime.now());
        return entity;
    }

    private void validateFields(E entity) {
        List<String> typeFieldNames = entity.getType().getAllFields().stream().map(Field::getName).collect(Collectors.toList());
        List<String> entityAttributeNames = new ArrayList<>(entity.getAttributes().keySet());
        entityAttributeNames.removeAll(typeFieldNames);
        if (!entityAttributeNames.isEmpty()) {
            throw new InvalidFieldException(entityAttributeNames);
        }
    }

    @Override
    public E deleteById(String id) {
        E entity = findById(id);
        // TODO: 06-Sep-18 check constraints
        entityDao.delete(entity);
        return entity;
    }
}
