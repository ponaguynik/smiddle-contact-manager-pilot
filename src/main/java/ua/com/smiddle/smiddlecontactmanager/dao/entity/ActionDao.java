package ua.com.smiddle.smiddlecontactmanager.dao.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;

import java.util.List;

public interface ActionDao extends EntityDao<Action, ActionType> {

    List<Action> findAllByAppealId(String appealId);
}
