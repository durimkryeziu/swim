package org.prnhs.javaee.swim.services;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.prnhs.javaee.swim.dto.ProgramDto;


public class ProgramValidator implements ConstraintValidator<ValidateProgram, ProgramDto>{

    @Override
    public void initialize(ValidateProgram a) {
    }

    @Override
    public boolean isValid(ProgramDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return false;
        }

        if (StringUtils.isNumeric(dto.getObjective())) {
            return false;
        }
        return true;
    }
    
}
