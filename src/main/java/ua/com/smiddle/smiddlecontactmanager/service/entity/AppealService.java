package ua.com.smiddle.smiddlecontactmanager.service.entity;

import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;

import java.util.List;

public interface AppealService extends EntityService<Appeal, AppealType> {

    List<Appeal> findAllByClientId(String clientId);
}
