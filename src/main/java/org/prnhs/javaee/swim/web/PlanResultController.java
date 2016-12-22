package org.prnhs.javaee.swim.web;

import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.prnhs.javaee.swim.services.PlanResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 15, 2016.
 */
@RestController
@RequestMapping("/results")
public class PlanResultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanResultController.class);

    @Autowired
    private PlanResultService resultService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanResultDto save(@RequestBody PlanResultDto resultDto) {

        LOGGER.info("POST /results get called.");
        LOGGER.debug("Saving: {}", resultDto);

        return resultService.save(resultDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanResultDto getById(@PathVariable Integer id) {

        LOGGER.info("GET /results/{} get called.", id);

        PlanResultDto resultDto = resultService.getById(id);

        LOGGER.debug("Getting: {}", resultDto);

        return resultDto;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlanResultDto> getAll() {

        LOGGER.info("GET /results get called.");

        List<PlanResultDto> all = resultService.getAll();

        LOGGER.debug("{} 'Plan Results' got found.", all.size());
        LOGGER.debug("Getting: {}", all);

        return all;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

        LOGGER.info("DELETE /results/{} get called.", id);

        resultService.delete(id);
    }
}