package ua.com.smiddle.smiddlecontactmanager.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.smiddle.smiddlecontactmanager.model.entity.Client;
import ua.com.smiddle.smiddlecontactmanager.service.entity.ClientService;
import ua.com.smiddle.smiddlecontactmanager.validator.ClientValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientValidator clientValidator;

    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(clientValidator);
    }

    @RequestMapping(path = "/{clientId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Client findById(@PathVariable String clientId) {
        return clientService.findById(clientId);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = "typeId",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Client> findAllByTypeId(@RequestParam String typeId) {
        return clientService.findAllByTypeId(typeId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "fixed",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Client> findByFixed(@RequestParam String fixed) {
        return clientService.findByFixed(fixed);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.create(client));
    }

    @RequestMapping(path = "/{clientId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Client update(@PathVariable String clientId, @Valid @RequestBody Client client) {
        client.setId(clientId);
        return clientService.update(client);
    }

    @RequestMapping(path = "/{clientId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Client delete(@PathVariable String clientId) {
        return clientService.deleteById(clientId);
    }
}
