package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.entity.PlanResult;
import org.prnhs.javaee.swim.dto.PlanResultDto;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
public class PlanResultTranslator {

    public static PlanResultDto toDto(PlanResult entity) {

        if (entity == null) {
            throw new IllegalArgumentException("You have to provide the Plan Result!");
        }

        PlanResultDto dto = new PlanResultDto();
        dto.setKey(entity.getId());
        dto.setStrokes(entity.getStrokes());
        dto.setLength(entity.getLength());
        dto.setSwimTime(entity.getSwimTime());

        return dto;
    }

    public static PlanResult toEntity(PlanResultDto dto) {

        if (dto == null) {
            throw new IllegalArgumentException("You have to provide the Plan Result!");
        }

        PlanResult entity = new PlanResult();
        entity.setId(dto.getKey());
        entity.setStrokes(dto.getStrokes());
        entity.setLength(dto.getLength());
        entity.setSwimTime(dto.getSwimTime());

        return entity;
    }
}