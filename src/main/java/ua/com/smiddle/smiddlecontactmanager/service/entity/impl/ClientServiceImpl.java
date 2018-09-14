package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ClientDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ClientService;
import ua.com.smiddle.smiddlecontactmanager.service.type.ClientTypeService;

import java.util.List;

@Service
public class ClientServiceImpl extends AbstractEntityService<Client, ClientType> implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ClientTypeService typeService) {
        super(clientDao, typeService);
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> findByFixed(String fixed) {
        return clientDao.findByFixed(fixed);
    }

    @Override
    public Client create(Client entity) {
        return clientDao.save(super.create(entity));
    }

    @Override
    public Client update(Client entity) {
        return clientDao.save(super.update(entity));
    }
}
