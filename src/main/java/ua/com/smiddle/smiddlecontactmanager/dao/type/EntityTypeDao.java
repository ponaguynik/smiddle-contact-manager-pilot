package ua.com.smiddle.smiddlecontactmanager.dao.type;

import ua.com.smiddle.smiddlecontactmanager.dao.GenericDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.Optional;

public interface EntityTypeDao<T extends EntityType<T, E>, E extends Entity<E, T>> extends GenericDao<T, String> {

    Optional<T> findByName(String name);
}
