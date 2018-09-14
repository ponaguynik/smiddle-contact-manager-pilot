package ua.com.smiddle.smiddlecontactmanager.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Action;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ActionService;
import ua.com.smiddle.smiddlecontactmanager.validator.ActionValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/actions")
public class ActionController {
    private final ActionService actionService;
    private final ActionValidator actionValidator;

    @Autowired
    public ActionController(ActionService actionService, ActionValidator actionValidator) {
        this.actionService = actionService;
        this.actionValidator = actionValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(actionValidator);
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Action findById(@PathVariable String actionId) {
        return actionService.findById(actionId);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Action> findAll() {
        return actionService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = "typeId",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Action> findAllByTypeId(@RequestParam String typeId) {
        return actionService.findAllByTypeId(typeId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "appealId",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Action> findAllByAppealId(@RequestParam String appealId) {
        return actionService.findAllByAppealId(appealId);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Action> create(@Valid @RequestBody Action action) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(actionService.create(action));
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Action update(@PathVariable String actionId, @Valid @RequestBody Action action) {
        action.setId(actionId);
        return actionService.update(action);
    }

    @RequestMapping(path = "/{actionId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Action delete(@PathVariable String actionId) {
        return actionService.deleteById(actionId);
    }
}
