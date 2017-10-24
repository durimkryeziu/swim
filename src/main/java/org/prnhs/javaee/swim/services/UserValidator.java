package org.prnhs.javaee.swim.services;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.prnhs.javaee.swim.dto.UserDto;

/**
 *
 * @author mosgjig
 */
public class UserValidator implements ConstraintValidator<ValidateUser, UserDto> {

    
    @Override
    public void initialize(final ValidateUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (!StringUtils.isAlpha(value.getPassword())) {
            return false;
        }
        return true;
    }
}
