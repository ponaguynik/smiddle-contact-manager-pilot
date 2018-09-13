package ua.com.smiddle.smiddlecontactmanager.service.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.type.ActionTypeDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;
import ua.com.smiddle.smiddlecontactmanager.service.type.AbstractEntityTypeService;
import ua.com.smiddle.smiddlecontactmanager.service.type.ActionTypeService;

@Service
public class ActionTypeServiceImpl extends AbstractEntityTypeService<ActionType, Action> implements ActionTypeService {
    private final ActionTypeDao actionTypeDao;

    @Autowired
    public ActionTypeServiceImpl(ActionTypeDao actionTypeDao) {
        super(actionTypeDao);
        this.actionTypeDao = actionTypeDao;
    }
}
