package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
public class PlanPracticeTranslator {

    public static PlanPracticeDto toDto(PlanPractice entity) {

        if (entity == null) {
            throw new IllegalArgumentException("You have to provide the Plan Practice!");
        }

        PlanPracticeDto dto = new PlanPracticeDto();
        dto.setKey(entity.getId());
        dto.setLength(entity.getLength());
        dto.setMultiple(entity.getMultiple());
        dto.setExercise(entity.getExercise());
        dto.setSplit(entity.getSplit());

        return dto;
    }

    public static PlanPractice toEntity(PlanPracticeDto dto) {

        if (dto == null) {
            throw new IllegalArgumentException("You have to provide the Plan Practice!");
        }

        PlanPractice entity = new PlanPractice();
        entity.setId(dto.getKey());
        entity.setLength(dto.getLength());
        entity.setMultiple(dto.getMultiple());
        entity.setExercise(dto.getExercise());
        entity.setSplit(dto.getSplit());

        return entity;
    }
}