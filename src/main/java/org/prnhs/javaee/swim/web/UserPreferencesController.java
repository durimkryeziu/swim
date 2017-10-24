package org.prnhs.javaee.swim.web;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.prnhs.javaee.swim.services.UserPreferencesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserPreferences Controller")
@RequestMapping("/userPreferences")
public class UserPreferencesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPreferencesController.class);

    @Autowired
    private MetricRegistry metrics;

    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private UserPreferencesService userPreferencesService;

    @PostConstruct
    private void startProfiler() {
        //    reporter.start(15, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a userPreference", notes = "UserPreferences are either created or updated depending whether a username is present or not.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserPreferencesDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public UserPreferencesDto save(@RequestBody @Valid @NotNull(message = "{user.preference.null}") UserPreferencesDto dto) {
        LOGGER.debug("Save method has been called, saving username {}", dto.getUsername());
        Meter requests = metrics.meter("save");
        requests.mark();

        UserPreferencesDto savedItem = userPreferencesService.save(dto);
        Link link = ControllerLinkBuilder.linkTo(UserPreferencesController.class).slash(savedItem.getUsername()).withSelfRel();
        savedItem.add(link);

        return savedItem;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrive a userPreference by username", notes = "Retrive the userPreference by their username")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserPreferencesDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public UserPreferencesDto getByUsername(@PathVariable String username) {
        LOGGER.debug("getByUsername method has been called with the username: {}", username);
        Meter requests = metrics.meter("getByUsername");
        requests.mark();

        UserPreferencesDto dto = userPreferencesService.getByUsername(username);
        Link link = ControllerLinkBuilder.linkTo(ContactController.class).slash(dto.getUsername()).withSelfRel();
        dto.add(link);

        return dto;
    }

    @ApiOperation(value = "Retrieve all userPreference", notes = "Retrieve all the userPreferences in the system")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserPreferencesDto.class)})
    public List<UserPreferencesDto> getAll() {
        LOGGER.debug("GetAll method has been called");
        Meter requests = metrics.meter("getAll");
        requests.mark();
        List<UserPreferencesDto> userPreferences = userPreferencesService.getAll();
        userPreferences.stream().forEach(userPreference -> userPreference.add(ControllerLinkBuilder.linkTo(UserPreferencesController.class).slash(userPreference.getUsername()).withSelfRel()));
        return userPreferencesService.getAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a userPreference", notes = "For given username, find userPreference and delete.")
    public void delete(@PathVariable String username) {
        LOGGER.debug("Delete method with username {} has been called", username);
        userPreferencesService.delete(username);
    }

}
