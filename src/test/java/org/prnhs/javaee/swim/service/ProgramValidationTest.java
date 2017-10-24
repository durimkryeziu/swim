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
import org.prnhs.javaee.swim.dto.ProgramDto;

public class ProgramValidationTest {
    
    private static final String OBJECTIVE = "Some objctive";
    private static final int ID = 20;
    
    private Validator validator;
    private ProgramDto program;
    
    @Before
    public void init(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
        
        program = new ProgramDto();
        program.setKey(ID);
        program.setObjective(OBJECTIVE);
    }
    
    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<ProgramDto>> violations = this.validator.validate(program);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void test_NullObjective() {
        program.setObjective(null);
        Set<ConstraintViolation<ProgramDto>> violations = this.validator.validate(program);
        assertTrue(!violations.isEmpty());
    }
    
    @Test
    public void test_LongObjective() {
        program.setObjective(RandomStringUtils.randomAlphabetic(301));
        Set<ConstraintViolation<ProgramDto>> violations = this.validator.validate(program);
        assertTrue(!violations.isEmpty());
    }
    
    @Test
    public void test_ShortObjective() {
        program.setObjective("");
        Set<ConstraintViolation<ProgramDto>> violations = this.validator.validate(program);
        assertTrue(!violations.isEmpty());
    }
    
    @Test
    public void test_numericObjective(){
        program.setObjective(RandomStringUtils.randomNumeric(3));
        Set<ConstraintViolation<ProgramDto>> violations = this.validator.validate(program);
        assertTrue(!violations.isEmpty());
    }
}
