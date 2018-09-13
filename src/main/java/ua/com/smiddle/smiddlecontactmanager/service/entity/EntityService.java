package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.List;

public interface EntityService<E extends Entity<E, T>, T extends EntityType<T, E>> {

    E create(E type);

    List<E> findAll();

    E findById(String id);

    E update(E type);

    E deleteById(String id);

    List<E> findAllByTypeId(String typeId);
}
