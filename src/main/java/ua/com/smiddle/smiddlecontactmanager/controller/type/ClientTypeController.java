package ua.com.smiddle.smiddlecontactmanager.controller.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.type.ClientType;
import ua.com.smiddle.smiddlecontactmanager.service.type.ClientTypeService;
import ua.com.smiddle.smiddlecontactmanager.template.EntityTypeTemplate;
import ua.com.smiddle.smiddlecontactmanager.validator.ClientTypeValidator;

import java.util.List;

@RestController
@RequestMapping("/client-types")
public class ClientTypeController {
    private final ClientTypeService clientTypeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ClientTypeValidator());
    }

    @Autowired
    public ClientTypeController(ClientTypeService clientTypeService) {
        this.clientTypeService = clientTypeService;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ClientType> findAll() {
        return clientTypeService.findAll();
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientType findById(@PathVariable String typeId) {
        return clientTypeService.findById(typeId);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientType> create(@RequestBody @Validated ClientType clientType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientTypeService.create(clientType));
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientType update(@PathVariable String typeId, @RequestBody ClientType clientType) {
        clientType.setId(typeId);
        return clientTypeService.update(clientType);
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientType delete(@PathVariable String typeId) {
        return clientTypeService.deleteById(typeId);
    }

    @RequestMapping(path = "/template", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientType template() {
        return EntityTypeTemplate.CLIENT_TYPE.getTemplate();
    }
}
