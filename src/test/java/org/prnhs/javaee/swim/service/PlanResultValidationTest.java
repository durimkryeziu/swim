package org.prnhs.javaee.swim.service;

import org.junit.Before;
import org.junit.Test;
import org.prnhs.javaee.swim.dto.PlanResultDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Durim Kryeziu on Dec 29, 2016.
 */
public class PlanResultValidationTest {

    private static final Integer ID = 5;
    private static final Integer STROKES = 100;
    private static final Integer LENGTH = 50;
    private static final Time SWIM_TIME = Time.valueOf(LocalTime.now());

    private PlanResultDto planResult;

    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        planResult = new PlanResultDto();
        planResult.setKey(ID);
        planResult.setStrokes(STROKES);
        planResult.setLength(LENGTH);
        planResult.setSwimTime(SWIM_TIME);
    }

    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<PlanResultDto>> violations = this.validator.validate(planResult);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullKey() {
        planResult.setKey(null);
        Set<ConstraintViolation<PlanResultDto>> violations = this.validator.validate(planResult);
        assertTrue(!violations.isEmpty());
    }
}