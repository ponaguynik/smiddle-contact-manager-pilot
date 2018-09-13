package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;

import java.util.List;

public interface ActionService extends EntityService<Action, ActionType> {

    List<Action> findAllByAppealId(String appealId);
}
