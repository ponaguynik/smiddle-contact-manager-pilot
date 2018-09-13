package ua.com.smiddle.smiddlecontactmanager.service.type;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.List;

public interface EntityTypeService<T extends EntityType<T, E>, E extends Entity<E, T>> {

    T create(T type);

    List<T> findAll();

    T findById(String id);

    T update(T type);

    T deleteById(String id);
}
