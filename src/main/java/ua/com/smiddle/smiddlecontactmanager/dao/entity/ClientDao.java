package ua.com.smiddle.smiddlecontactmanager.dao.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;

import java.util.List;

public interface ClientDao extends EntityDao<Client, ClientType> {
    List<Client> findByFixed(String fixed);
}
