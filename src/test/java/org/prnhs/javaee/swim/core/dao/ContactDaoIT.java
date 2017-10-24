package org.prnhs.javaee.swim.core.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase ( replace = Replace.NONE)
public class ContactDaoIT {

    private static final Logger logger = LoggerFactory.getLogger(ContactDaoIT.class);

    @Autowired
    private ContactsDao contactsDao;
    @PersistenceContext
    private EntityManager entityManager;

    private static final Integer Id = 1;
    private static final String TITLE = "student";
    private static final String FIRST_NAME = "Vlera";
    private static final String MIDDLE_NAME = "vl";
    private static final String LAST_NAME = "Curri";

    private Contacts contacts;

    @Before
    public void onStartUp(){
        contacts = new Contacts();

        contacts.setId(Id);
        contacts.setTitle(TITLE);
        contacts.setFirstName(FIRST_NAME);
        contacts.setMiddleName(MIDDLE_NAME);
        contacts.setLastName(LAST_NAME);
    }

    @Test
    public void testSave(){
        contactsDao.save(contacts);
        assertNotNull(contacts.getId());
    }

    @Test
    public void testFindOne(){
        contactsDao.save(contacts);
        entityManager.flush();
        assertNotNull(contacts);

        Contacts savedContact = contactsDao.findOne(contacts.getId());

        assertNotNull(savedContact);
        assertEquals(contacts, savedContact);
    }

    @Test
    public void testFindAll() {
        contactsDao.save(contacts);
        Iterable<Contacts> entities = contactsDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }

    @Test
    public void testUpdate(){
        contactsDao.save(contacts);
        entityManager.flush();
        assertNotNull(contacts.getId());
        Contacts savedContact = contactsDao.findOne(contacts.getId());

        assertEquals(contacts, savedContact);

        savedContact.setFirstName("argita");

        Contacts updatedContact = contactsDao.save(savedContact);
        assertNotNull(updatedContact);
        assertEquals(savedContact, updatedContact);
    }

    @Test
    public void testDelete() {
        contactsDao.save(contacts);
        entityManager.flush();
        assertNotNull(contacts.getId());
        contactsDao.delete(contacts.getId());
        Contacts entity = contactsDao.findOne(contacts.getId());
        assertNull(entity);
    }
}
