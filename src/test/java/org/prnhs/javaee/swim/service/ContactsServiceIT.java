
package org.prnhs.javaee.swim.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.prnhs.javaee.swim.core.dao.ContactsDao;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.prnhs.javaee.swim.dto.ContactsDto;
import org.prnhs.javaee.swim.services.ContactsServices;

import java.util.List;

import static org.mockito.Mockito.when;

public class ContactsServiceIT {

    public static final Integer ID = 1;
    public static final String FIRSTNAME = "vlera";
    public static final String MIDDLENAME = "vl";
    public static final String LASTNAME = "curri";
    public static final String TITLE = "developer";

    @InjectMocks
    private ContactsServices service;

    @Mock
    private ContactsDao dao;

    private ContactsDto dto;
    private List<ContactsDto> dtos;
    private Contacts contact;
    private List<Contacts> contacts;

    @Before
    public void onStartUp(){

        MockitoAnnotations.initMocks(this);

        contact = new Contacts();
        contact.setId(ID);
        contact.setFirstName(FIRSTNAME);
        contact.setMiddleName(MIDDLENAME);
        contact.setLastName(LASTNAME);
        contact.setTitle(TITLE);

        dto = new ContactsDto();
        dto.setKey(ID);
        dto.setFirstName(FIRSTNAME);
        dto.setMiddleName(MIDDLENAME);
        dto.setLastName(LASTNAME);
        dto.setTitle(TITLE);
    }

    @Test
    public void test_saveWithFound() {

        when(dao.findOne(Matchers.anyInt())).thenReturn(contact);
        when(dao.save(contact)).thenReturn(contact);

        ContactsDto savedDto = service.save(dto);
        Mockito.verify(dao).save(contact);
    }

    @Test
    public void test_saveNotFound() {

        when(dao.findOne(Matchers.anyInt())).thenReturn(null);
        when(dao.save(contact)).thenReturn(contact);

        ContactsDto savedDto = service.save(dto);
        Mockito.verify(dao).save(contact);
    }

    @Test
    public void test_getById() {

        when(dao.findOne(ID)).thenReturn(contact);

        dto = service.getById(ID);
        Mockito.verify(dao).findOne(ID);
    }

    @Test
    public void test_getByIdNotFound() {

        when(dao.findOne(ID)).thenReturn(null);

        dto = service.getById(ID);
        Mockito.verify(dao).findOne(ID);
    }

    @Test
    public void test_getAll() {

        when(dao.findAll()).thenReturn(contacts);

        dtos = service.getAll();
        Mockito.verify(dao).findAll();
    }

    @Test
    public void test_getAllFailed() {

        when(dao.findAll()).thenReturn(null);

        dtos = service.getAll();
        Mockito.verify(dao).findAll();
    }

    @Test
    public void test_delete() {

        when(dao.findOne(ID)).thenReturn(contact);

        service.delete(ID);
        Mockito.verify(dao).delete(contact);
    }
}
