package ua.com.smiddle.smiddlecontactmanager.dao.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;

import java.util.List;

public interface AppealDao extends EntityDao<Appeal, AppealType> {

    List<Appeal> findAllByClientId(String clientId);
}
