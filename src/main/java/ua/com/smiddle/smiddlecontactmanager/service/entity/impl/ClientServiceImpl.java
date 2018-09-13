package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ClientDao;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.impl.ClientDaoImpl;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ClientService;

@Service
public class ClientServiceImpl extends AbstractEntityService<Client, ClientType> implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        super(clientDao);
        this.clientDao = clientDao;
    }
}
