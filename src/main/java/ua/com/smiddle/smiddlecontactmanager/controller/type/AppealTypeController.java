package ua.com.smiddle.smiddlecontactmanager.controller.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.type.AppealType;
import ua.com.smiddle.smiddlecontactmanager.service.type.AppealTypeService;
import ua.com.smiddle.smiddlecontactmanager.template.EntityTypeTemplate;
import ua.com.smiddle.smiddlecontactmanager.validator.EntityTypeValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appeal-types")
public class AppealTypeController {
    private final AppealTypeService appealTypeService;

    @Autowired
    public AppealTypeController(AppealTypeService appealTypeService) {
        this.appealTypeService = appealTypeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new EntityTypeValidator());
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AppealType> create(@Valid @RequestBody AppealType appealType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appealTypeService.create(appealType));
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppealType> findAll() {
        return appealTypeService.findAll();
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppealType findById(@PathVariable String appealId) {
        return appealTypeService.findById(appealId);
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppealType update(@PathVariable String appealId, @Valid @RequestBody AppealType appealType) {
        appealType.setId(appealId);
        return appealTypeService.update(appealType);
    }

    @RequestMapping(path = "/{appealId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppealType delete(@PathVariable String appealId) {
        return appealTypeService.deleteById(appealId);
    }

    @RequestMapping(path = "/template", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppealType template() {
        return EntityTypeTemplate.APPEAL_TYPE.getTemplate();
    }
}
