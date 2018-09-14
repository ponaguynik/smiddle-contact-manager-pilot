package ua.com.smiddle.smiddlecontactmanager.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Appeal;
import ua.com.smiddle.smiddlecontactmanager.service.entity.AppealService;
import ua.com.smiddle.smiddlecontactmanager.validator.AppealValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appeals")
public class AppealController {
    private final AppealService appealService;
    private final AppealValidator appealValidator;

    @Autowired
    public AppealController(AppealService appealService, AppealValidator appealValidator) {
        this.appealService = appealService;
        this.appealValidator = appealValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(appealValidator);
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Appeal findById(@PathVariable String appealId) {
        return appealService.findById(appealId);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Appeal> findAll() {
        return appealService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = "typeId",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Appeal> findAllByTypeId(@RequestParam String typeId) {
        return appealService.findAllByTypeId(typeId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "clientId",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Appeal> findAllByClientId(@RequestParam String clientId) {
        return appealService.findAllByClientId(clientId);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Appeal> create(@Valid @RequestBody Appeal appeal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appealService.create(appeal));
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Appeal update(@PathVariable String appealId, @Valid @RequestBody Appeal appeal) {
        appeal.setId(appealId);
        return appealService.update(appeal);
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Appeal delete(@PathVariable String appealId) {
        return appealService.deleteById(appealId);
    }
}
