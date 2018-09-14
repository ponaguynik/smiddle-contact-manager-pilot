package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;

import java.util.List;

public interface ClientService extends EntityService<Client, ClientType> {

    List<Client> findByFixed(String fixed);
}
