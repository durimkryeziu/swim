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
import org.prnhs.javaee.swim.dto.UserDto;
import org.prnhs.javaee.swim.services.UserService;
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
@Api(value = "User Controller")
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MetricRegistry metrics;

    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void startProfiler() {
//        reporter.start(15, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a user", notes = "Users are either created or updated depending whether an id/key is present or not.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public UserDto save(@RequestBody @Valid @NotNull(message = "{user.null}") UserDto dto) {
        Meter requests = metrics.meter("save");
        requests.mark();

        UserDto savedItem = userService.save(dto);

        Link link = ControllerLinkBuilder.linkTo(UserController.class).slash(savedItem.getUsername()).withSelfRel();
        savedItem.add(link);

        return savedItem;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve a user by id", notes = "Retrieve the user by their id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public UserDto getById(@PathVariable String id) {

        LOGGER.debug("hello from getById {} ", id);
        Meter requests = metrics.meter("getById");
        requests.mark();

        UserDto dto = userService.getById(id);

        Link link = ControllerLinkBuilder.linkTo(UserController.class).slash(dto.getUsername()).withSelfRel();
        dto.add(link);

        return dto;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve all user", notes = "Retrieve all the users in the system")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserDto.class)})
    public List<UserDto> getAll() {
        Meter requests = metrics.meter("getAll");
        requests.mark();

        List<UserDto> users = userService.getAll();
        users.stream().forEach(user -> user.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUsername()).withSelfRel()));

        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a user", notes = "For given id, find user and delete.")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
