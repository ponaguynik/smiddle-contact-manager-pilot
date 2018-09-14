package ua.com.smiddle.smiddlecontactmanager.controller.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.type.ActionType;
import ua.com.smiddle.smiddlecontactmanager.service.type.ActionTypeService;
import ua.com.smiddle.smiddlecontactmanager.template.EntityTypeTemplate;
import ua.com.smiddle.smiddlecontactmanager.validator.EntityTypeValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/action-types")
public class ActionTypeController {
    private final ActionTypeService actionTypeService;

    @Autowired
    public ActionTypeController(ActionTypeService actionTypeService) {
        this.actionTypeService = actionTypeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new EntityTypeValidator());
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ActionType> findAll() {
        return actionTypeService.findAll();
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ActionType findById(@PathVariable String actionId) {
        return actionTypeService.findById(actionId);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ActionType> create(@Valid @RequestBody ActionType actionType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(actionTypeService.create(actionType));
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ActionType update(@PathVariable String actionId, @Valid @RequestBody ActionType actionType) {
        actionType.setId(actionId);
        return actionTypeService.update(actionType);
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ActionType delete(@PathVariable String actionId) {
        return actionTypeService.deleteById(actionId);
    }

    @RequestMapping(path = "/template", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ActionType template() {
        return EntityTypeTemplate.ACTION_TYPE.getTemplate();
    }
}
