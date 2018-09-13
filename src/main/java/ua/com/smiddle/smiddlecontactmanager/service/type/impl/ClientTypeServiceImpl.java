package ua.com.smiddle.smiddlecontactmanager.service.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.type.ClientTypeDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.service.type.AbstractEntityTypeService;
import ua.com.smiddle.smiddlecontactmanager.service.type.ClientTypeService;

@Service
public class ClientTypeServiceImpl extends AbstractEntityTypeService<ClientType, Client> implements ClientTypeService {
    private final ClientTypeDao clientTypeDao;

    @Autowired
    public ClientTypeServiceImpl(ClientTypeDao clientTypeDao) {
        super(clientTypeDao);
        this.clientTypeDao = clientTypeDao;
    }
}
