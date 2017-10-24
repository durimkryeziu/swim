package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.PlanResultDao;
import org.prnhs.javaee.swim.core.entity.PlanResult;
import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@Service
public class PlanResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanResultService.class);

    @Autowired
    private PlanResultDao dao;

    public PlanResultDto save(PlanResultDto planResultDto) {

        if (planResultDto == null) {
            LOGGER.error("'Plan Result' is missing!");
            throw new IllegalArgumentException("You have to provide the Plan Result!");
        }

        PlanResult planResult = dao.findOne(planResultDto.getKey());

        if (planResult == null) {
            LOGGER.debug("'Given 'Plan Result' is not found, creating a new one.");
            planResult = PlanResultTranslator.toEntity(planResultDto);
        } else {
            LOGGER.debug("Updating {}", planResultDto);
            planResult.setStrokes(planResultDto.getStrokes());
            planResult.setLength(planResultDto.getLength());
            planResult.setSwimTime(planResultDto.getSwimTime());
        }

        planResult = dao.save(planResult);
        LOGGER.debug("Saved successfully with result {}", planResultDto);

        return PlanResultTranslator.toDto(planResult);
    }

    public PlanResultDto getById(Integer id) {
        PlanResultDto dto = null;

        LOGGER.debug("Trying to find 'Plan Result' with id {}", id);

        PlanResult planResult = dao.findOne(id);
        if (planResult != null) {
            dto = PlanResultTranslator.toDto(planResult);
            LOGGER.debug("'Plan Result' with id {} got found successfully!", id);
        }

        return dto;
    }

    public List<PlanResultDto> getAll() {

        LOGGER.debug("Trying to get all 'Plan Results'");

        Iterable<PlanResult> planResults = dao.findAll();

        List<PlanResultDto> dtos = new ArrayList<>();

        planResults.forEach(planResult -> dtos.add(PlanResultTranslator.toDto(planResult)));

        return dtos;
    }

    public void delete(Integer id) {
        PlanResult planResult = dao.findOne(id);
        if (planResult != null) {
            LOGGER.debug("Deleting 'Plan Result' with id: {}", id);
            dao.delete(planResult);
        } else {
            LOGGER.debug("'Plan Result' with id {} was not found to get deleted.", id);
        }
    }
}