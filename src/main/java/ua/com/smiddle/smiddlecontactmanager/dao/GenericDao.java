package ua.com.smiddle.smiddlecontactmanager.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E, ID> {

    E save(E type);

    Optional<E> findById(ID id);

    List<E> findAll();

    void delete(E e);
}
