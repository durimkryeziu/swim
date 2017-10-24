package org.prnhs.javaee.swim.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;
import org.prnhs.javaee.swim.services.PlanPracticeService;
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
@Api("Plan Practice Controller")
@RequestMapping("/practices")
public class PlanPracticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanPracticeController.class);

    @Autowired
    private PlanPracticeService practiceService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a Plan Practice", notes = "Plan Practice is either created or updated depending whether the id is present on the database or not.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanPracticeDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public PlanPracticeDto save(@RequestBody
                                @Valid
                                @NotNull(message = "{plan.practice.null}") PlanPracticeDto practiceDto) {

        LOGGER.info("POST /practices get called.");
        LOGGER.debug("Saving: {}", practiceDto);

        PlanPracticeDto saved = practiceService.save(practiceDto);

        Link link = linkTo(PlanPracticeController.class)
                .slash(saved.getKey())
                .withSelfRel();

        saved.add(link);

        return saved;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve a Plan Practice by id", notes = "Retrieve the Plan Practice by their id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanPracticeDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public PlanPracticeDto getById(@PathVariable Integer id) {

        LOGGER.info("GET /practices/{} get called.", id);

        PlanPracticeDto practiceDto = practiceService.getById(id);

        LOGGER.debug("Getting: {}", practiceDto);

        Link link = linkTo(PlanPracticeController.class)
                .slash(practiceDto.getKey())
                .withSelfRel();

        practiceDto.add(link);

        return practiceDto;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve all Plan Practices", notes = "Retrieves all the Plan Practices in the system")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PlanPracticeDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<PlanPracticeDto> getAll() {

        LOGGER.info("GET /practices get called.");

        List<PlanPracticeDto> all = practiceService.getAll();

        LOGGER.debug("{} 'Plan Practices' got found.", all.size());
        LOGGER.debug("Getting: {}", all);

        all.stream()
                .forEach(practiceDto -> practiceDto.add(linkTo(PlanPracticeController.class)
                        .slash(practiceDto.getKey())
                        .withSelfRel()));

        return all;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a Plan Practice", notes = "For given id, find the Plan Practice and delete")
    @ApiResponse(code = 204, message = "No Content")
    public void delete(@PathVariable Integer id) {

        LOGGER.info("DELETE /practices/{} get called.", id);

        practiceService.delete(id);
    }
}