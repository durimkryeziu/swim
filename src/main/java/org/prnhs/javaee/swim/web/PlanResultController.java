package org.prnhs.javaee.swim.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.prnhs.javaee.swim.services.PlanResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Durim Kryeziu on Dec 15, 2016.
 */
@RestController
@Api("Plan Result Controller")
@RequestMapping("/results")
public class PlanResultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanResultController.class);

    @Autowired
    private PlanResultService resultService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a Plan Result", notes = "Plan Result is either created or updated depending whether the id is present on the database or not.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanResultDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public PlanResultDto save(@RequestBody
                              @Valid
                              @NotNull(message = "{plan.result.null}") PlanResultDto resultDto) {

        LOGGER.info("POST /results get called.");
        LOGGER.debug("Saving: {}", resultDto);

        PlanResultDto saved = resultService.save(resultDto);

        Link link = linkTo(PlanResultController.class)
                .slash(saved.getKey())
                .withSelfRel();

        saved.add(link);

        return saved;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve a Plan Result by id", notes = "Retrieve the Plan Result by their id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanResultDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public PlanResultDto getById(@PathVariable Integer id) {

        LOGGER.info("GET /results/{} get called.", id);

        PlanResultDto resultDto = resultService.getById(id);

        LOGGER.debug("Getting: {}", resultDto);

        Link link = linkTo(PlanResultController.class)
                .slash(resultDto.getKey())
                .withSelfRel();

        resultDto.add(link);

        return resultDto;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve all Plan Results", notes = "Retrieves all the Plan Results in the system")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanResultDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<PlanResultDto> getAll() {

        LOGGER.info("GET /results get called.");

        List<PlanResultDto> all = resultService.getAll();

        LOGGER.debug("{} 'Plan Results' got found.", all.size());
        LOGGER.debug("Getting: {}", all);

        all.stream()
                .forEach(practiceDto -> practiceDto.add(linkTo(PlanResultController.class)
                        .slash(practiceDto.getKey())
                        .withSelfRel()));

        return all;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a Plan Result", notes = "For given id, find the Plan Result and delete")
    @ApiResponse(code = 204, message = "No Content")
    public void delete(@PathVariable Integer id) {

        LOGGER.info("DELETE /results/{} get called.", id);

        resultService.delete(id);
    }
}