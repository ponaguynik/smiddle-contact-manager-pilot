package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.AppealDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AppealService;

import java.util.List;

@Service
public class AppealServiceImpl extends AbstractEntityService<Appeal, AppealType> implements AppealService {
    private final AppealDao appealDao;

    @Autowired
    public AppealServiceImpl(AppealDao appealDao) {
        super(appealDao);
        this.appealDao = appealDao;
    }

    @Override
    public List<Appeal> findAllByClientId(String clientId) {
        return appealDao.findAllByClientId(clientId);
    }
}
