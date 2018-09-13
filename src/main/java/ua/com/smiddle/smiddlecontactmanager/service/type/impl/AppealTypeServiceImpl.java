package ua.com.smiddle.smiddlecontactmanager.service.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.type.AppealTypeDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;
import ua.com.smiddle.smiddlecontactmanager.service.type.AbstractEntityTypeService;
import ua.com.smiddle.smiddlecontactmanager.service.type.AppealTypeService;

@Service
public class AppealTypeServiceImpl extends AbstractEntityTypeService<AppealType, Appeal> implements AppealTypeService {
    private final AppealTypeDao appealTypeDao;

    @Autowired
    public AppealTypeServiceImpl(AppealTypeDao appealTypeDao) {
        super(appealTypeDao);
        this.appealTypeDao = appealTypeDao;
    }
}
