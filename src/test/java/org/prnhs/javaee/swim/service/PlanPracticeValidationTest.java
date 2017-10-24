package org.prnhs.javaee.swim.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;

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
public class PlanPracticeValidationTest {

    private static final Integer ID = 7;
    private static final Integer LENGTH = 50;
    private static final Integer MULTIPLE = 5;
    private static final String EXERCISE = "The Freestyle Stroke";
    private static final Time SPLIT = Time.valueOf(LocalTime.now());

    private PlanPracticeDto planPractice;

    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        planPractice = new PlanPracticeDto();
        planPractice.setKey(ID);
        planPractice.setLength(LENGTH);
        planPractice.setMultiple(MULTIPLE);
        planPractice.setExercise(EXERCISE);
        planPractice.setSplit(SPLIT);
    }

    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<PlanPracticeDto>> violations = this.validator.validate(planPractice);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullKey() {
        planPractice.setKey(null);
        Set<ConstraintViolation<PlanPracticeDto>> violations = this.validator.validate(planPractice);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void testLongExercise() {
        planPractice.setExercise(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<PlanPracticeDto>> violations = this.validator.validate(planPractice);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void testShortExercise() {
        planPractice.setExercise("");
        Set<ConstraintViolation<PlanPracticeDto>> violations = this.validator.validate(planPractice);
        assertTrue(!violations.isEmpty());
    }
}