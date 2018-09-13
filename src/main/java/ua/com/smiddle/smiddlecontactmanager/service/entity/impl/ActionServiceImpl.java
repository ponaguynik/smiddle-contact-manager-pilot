package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ActionDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ActionService;

import java.util.List;

@Service
public class ActionServiceImpl extends AbstractEntityService<Action, ActionType> implements ActionService {
    private final ActionDao actionDao;

    @Autowired
    public ActionServiceImpl(ActionDao actionDao) {
        super(actionDao);
        this.actionDao = actionDao;
    }

    @Override
    public List<Action> findAllByAppealId(String appealId) {
        return actionDao.findAllByAppealId(appealId);
    }
}
