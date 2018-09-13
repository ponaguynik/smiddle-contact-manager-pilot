package ua.com.smiddle.smiddlecontactmanager.dao.entity;

import ua.com.smiddle.smiddlecontactmanager.dao.GenericDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Entity;
import ua.com.smiddle.smiddlecontactmanager.model.type.EntityType;

import java.util.List;

public interface EntityDao<E extends Entity<E, T>, T extends EntityType<T, E>> extends GenericDao<E, String> {

    List<E> findAllByTypeId(String typeId);
}
