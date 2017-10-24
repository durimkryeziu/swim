package org.prnhs.javaee.swim.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang3.RandomStringUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;

public class UserPreferencesValidationTest {

    private static final String USERNAME = "Argita";
    //private static final Integer TYPEID = 38;
    private Validator validator;

    private UserPreferencesDto userPreference;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        userPreference = new UserPreferencesDto();
        userPreference.setUsername(USERNAME);
        // userPreference.setTypeId(TYPEID);
    }

    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<UserPreferencesDto>> violations = this.validator.validate(userPreference);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void test_NullUsername() {
        userPreference.setUsername(null);
        Set<ConstraintViolation<UserPreferencesDto>> violations = this.validator.validate(userPreference);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongUsername() {
        userPreference.setUsername(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<UserPreferencesDto>> violations = this.validator.validate(userPreference);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_ShortUsername() {
        userPreference.setUsername("");
        Set<ConstraintViolation<UserPreferencesDto>> violations = this.validator.validate(userPreference);
        assertTrue(!violations.isEmpty());
    }

}
