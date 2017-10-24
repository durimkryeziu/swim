package org.prnhs.javaee.swim.web;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.prnhs.javaee.swim.dto.ContactsDto;
import org.prnhs.javaee.swim.services.ContactsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "Contact Controller")
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private MetricRegistry metrics;

    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private ContactsServices service;

    @PostConstruct
    private void startProfiler(){
//      reporter.start(15, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a contact", notes = "Contacts are either created or updated depending whether an id/key is present or not.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ContactsDto.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    public ContactsDto save(@RequestBody @Valid @NotNull(message = "{contact.null}") ContactsDto dto) {

        LOGGER.debug("A request to POST method saving '{}' as a Contact", dto.getFirstName());
        Meter requests = metrics.meter("save");
        requests.mark();

        ContactsDto savedItem = service.save(dto);

        Link link = ControllerLinkBuilder.linkTo(ContactController.class).slash(savedItem.getKey()).withSelfRel();
        savedItem.add(link);

        return savedItem;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve a contact by id", notes = "Retrieve the contact by their id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ContactsDto.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    public ContactsDto getById(@PathVariable Integer id){

        LOGGER.debug("A request to the GET method, returning a Contact by given ID of {} ", id);
        Meter requests = metrics.meter("getById");
        requests.mark();

        ContactsDto dto = service.getById(id);

        Link link = ControllerLinkBuilder.linkTo(ContactController.class).slash(dto.getKey()).withSelfRel();
        dto.add(link);

        return dto;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve all contacts", notes = "Retrieve all the contacts in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ContactsDto.class)
    })
    public List<ContactsDto> getAll() {

        LOGGER.debug(" A request to the GET method, returning all Contacts");
        Meter requests = metrics.meter("getAll");
        requests.mark();

        List<ContactsDto> contacts = service.getAll();
        contacts.stream().forEach(contact -> contact.add(ControllerLinkBuilder.linkTo(ContactController.class).slash(contact.getKey()).withSelfRel()));
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete contact", notes = "For given id, find contact and delete")
    public void delete(@PathVariable Integer id){

        LOGGER.debug(" A request to a DELETE method with a given Id of {} ", id);
        Meter requests = metrics.meter("delete");
        requests.mark();

        service.delete(id);
    }
}
