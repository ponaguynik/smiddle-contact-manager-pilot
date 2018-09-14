package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.AppealDao;
import ua.com.smiddle.smiddlecontactmanager.exception.UnableChangeClientException;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AppealService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ClientService;
import ua.com.smiddle.smiddlecontactmanager.service.type.AppealTypeService;

import java.util.List;
import java.util.Objects;

@Service
public class AppealServiceImpl extends AbstractEntityService<Appeal, AppealType> implements AppealService {
    private final AppealDao appealDao;
    private final ClientService clientService;

    @Autowired
    public AppealServiceImpl(AppealDao appealDao, AppealTypeService typeService, ClientService clientService) {
        super(appealDao, typeService);
        this.appealDao = appealDao;
        this.clientService = clientService;
    }

    @Override
    public List<Appeal> findAllByClientId(String clientId) {
        return appealDao.findAllByClientId(clientId);
    }

    @Override
    public Appeal create(Appeal appeal) {
        appeal = super.create(appeal);
        Client client = clientService.findById(appeal.getClient().getId());
        appeal.setClient(client);
        return appealDao.save(appeal);
    }

    @Override
    public Appeal update(Appeal entity) {
        Appeal appeal = super.update(entity);
        Appeal dbAppeal = this.findById(appeal.getId());
        if (!Objects.equals(dbAppeal.getClient().getId(), appeal.getClient().getId())) {
            throw new UnableChangeClientException();
        }
        return appealDao.save(appeal);
    }
}
