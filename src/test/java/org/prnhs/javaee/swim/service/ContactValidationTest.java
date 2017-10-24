package org.prnhs.javaee.swim.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.prnhs.javaee.swim.dto.ContactsDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ContactValidationTest {

    private static final Integer KEY = 1;
    private static final String FIRSTNAME = "Vlera";
    private static final String MIDDLENAME = "vl";
    private static final String LASTNAME = "Curri";
    private static final String TITLE = "DEVELOPER";

    private Validator validator;

    private ContactsDto contact;

    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        contact = new ContactsDto();
        contact.setKey(KEY);
        contact.setFirstName(FIRSTNAME);
        contact.setMiddleName(MIDDLENAME);
        contact.setLastName(LASTNAME);
        contact.setTitle(TITLE);
    }

    @Test
    public void prereqsMet() {
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void test_NullKey() {
        contact.setKey(null);
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_NullFirstName() {
        contact.setFirstName(null);
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongFirstName() {
        contact.setFirstName(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongMiddleName() {
        contact.setMiddleName(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_NullLastName() {
        contact.setLastName(null);
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongLastName() {
        contact.setLastName(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_NullTitle() {
        contact.setTitle(null);
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

    @Test
    public void test_LongTitle() {
        contact.setTitle(RandomStringUtils.randomAlphabetic(46));
        Set<ConstraintViolation<ContactsDto>> violations = this.validator.validate(contact);
        assertTrue(!violations.isEmpty());
    }

}
