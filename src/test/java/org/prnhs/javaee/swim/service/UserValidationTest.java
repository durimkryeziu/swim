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
import org.prnhs.javaee.swim.dto.UserDto;

public class UserValidationTest {

    private static final String USERNAME = "goodname";
    private static final String PASSWORD = "pwd";
    private Validator validator;

    private UserDto user;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        user = new UserDto();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setEnabled(Boolean.FALSE);
    }

    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<UserDto>> violations = this.validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void test_NullName() {
        user.setUsername(null);
        Set<ConstraintViolation<UserDto>> violations = this.validator.validate(user);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongName() {
        user.setUsername(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<UserDto>> violations = this.validator.validate(user);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_BadPwd() {
        user.setPassword(RandomStringUtils.randomNumeric(3));
        Set<ConstraintViolation<UserDto>> violations = this.validator.validate(user);
        assertTrue(!violations.isEmpty());
    }
}
