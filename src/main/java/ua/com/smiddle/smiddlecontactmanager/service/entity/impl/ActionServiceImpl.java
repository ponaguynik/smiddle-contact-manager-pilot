package ua.com.smiddle.smiddlecontactmanager.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.smiddle.smiddlecontactmanager.dao.entity.ActionDao;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AbstractEntityService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ActionService;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AppealService;
import ua.com.smiddle.smiddlecontactmanager.service.type.ActionTypeService;

import java.util.List;

@Service
public class ActionServiceImpl extends AbstractEntityService<Action, ActionType> implements ActionService {
    private final ActionDao actionDao;
    private final AppealService appealService;

    @Autowired
    public ActionServiceImpl(ActionDao actionDao, ActionTypeService typeService, AppealService appealService) {
        super(actionDao, typeService);
        this.actionDao = actionDao;
        this.appealService = appealService;
    }

    @Override
    public List<Action> findAllByAppealId(String appealId) {
        return actionDao.findAllByAppealId(appealId);
    }

    @Override
    public Action create(Action action) {
        action = super.create(action);
        Appeal appeal = appealService.findById(action.getAppeal().getId());
        action.setAppeal(appeal);
        return actionDao.save(action);
    }

    @Override
    public Action update(Action entity) {
        return actionDao.save(super.update(entity));
    }
}
