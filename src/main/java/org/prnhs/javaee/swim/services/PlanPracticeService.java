package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.PlanPracticeDao;
import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;
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
public class PlanPracticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanPracticeService.class);

    @Autowired
    private PlanPracticeDao dao;

    public PlanPracticeDto save(PlanPracticeDto planPracticeDto) {

        if (planPracticeDto == null) {
            LOGGER.error("'Plan Practice' is missing!");
            throw new IllegalArgumentException("You have to provide the Plan Practice!");
        }

        PlanPractice planPractice = dao.findOne(planPracticeDto.getKey());

        if (planPractice == null) {
            LOGGER.debug("'Given 'Plan Practice' is not found, creating a new one.");
            planPractice = PlanPracticeTranslator.toEntity(planPracticeDto);
        } else {
            LOGGER.debug("Updating {}", planPracticeDto);
            planPractice.setLength(planPracticeDto.getLength());
            planPractice.setMultiple(planPracticeDto.getMultiple());
            planPractice.setExercise(planPracticeDto.getExercise());
            planPractice.setSplit(planPracticeDto.getSplit());
        }

        planPractice = dao.save(planPractice);
        LOGGER.debug("Saved successfully with result {}", planPracticeDto);

        return PlanPracticeTranslator.toDto(planPractice);
    }

    public PlanPracticeDto getById(Integer id) {
        PlanPracticeDto dto = null;

        LOGGER.debug("Trying to find 'Plan Practice' with id {}", id);

        PlanPractice planPractice = dao.findOne(id);

        if (planPractice != null) {
            dto = PlanPracticeTranslator.toDto(planPractice);
            LOGGER.debug("'Plan Practice' with id {} got found successfully!", id);
        }

        return dto;
    }

    public List<PlanPracticeDto> getAll() {

        LOGGER.debug("Trying to get all 'Plan Practices'");

        Iterable<PlanPractice> planPractices = dao.findAll();

        List<PlanPracticeDto> dtos = new ArrayList<>();

        planPractices.forEach(planPractice -> dtos.add(PlanPracticeTranslator.toDto(planPractice)));

        return dtos;
    }

    public void delete(Integer id) {
        PlanPractice planPractice = dao.findOne(id);

        if (planPractice != null) {
            LOGGER.debug("Deleting 'Plan Practice' with id: {}", id);
            dao.delete(id);
        } else {
            LOGGER.debug("'Plan Practice' with id {} was not found to get deleted.", id);
        }
    }
}