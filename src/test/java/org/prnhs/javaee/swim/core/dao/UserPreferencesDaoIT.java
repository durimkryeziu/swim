package org.prnhs.javaee.swim.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserPreferencesDaoIT {

    private static final Logger logger = LoggerFactory.getLogger(UserPreferencesDaoIT.class);

    @Autowired
    private UserPreferencesDao userPreferencesDao;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String username = "Argita";
    private static final Integer typeId = 1;

    private UserPreferences userPref;

    @Before
    public void onStartUp() {
        userPref = new UserPreferences();
        userPref.setUsername(username);
        userPref.setTypeId(typeId);
    }

    @Test
    public void testSave() {
        userPreferencesDao.save(userPref);
        assertNotNull(userPref.getUsername());
    }

    @Test(expected = JpaSystemException.class)
    public void testSave_NullUsername() {
        userPref.setUsername(null);
        userPreferencesDao.save(userPref);
    }

    @Test
    public void testFindOne() {
        userPreferencesDao.save(userPref);
        entityManager.flush();
        assertNotNull(userPref.getUsername());

        UserPreferences savedEntity = userPreferencesDao.findOne(userPref.getUsername());

        assertNotNull(savedEntity);
        assertEquals(userPref, savedEntity);
    }

    @Test
    public void testFindAll() {
        userPreferencesDao.save(userPref);
        Iterable<UserPreferences> entities = userPreferencesDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }

    @Test
    public void testUpdate() {
        userPreferencesDao.save(userPref);
        entityManager.flush();
        assertNotNull(userPref.getUsername());
        UserPreferences savedEntity = userPreferencesDao.findOne(userPref.getUsername());

        savedEntity.setUsername("another name");

        UserPreferences updateEntity = userPreferencesDao.save(savedEntity);
        assertNotNull(updateEntity);
        assertEquals(savedEntity, updateEntity);
    }

    @Test
    public void testDelete() {
        userPreferencesDao.save(userPref);
        entityManager.flush();
        assertNotNull(userPref.getUsername());
        userPreferencesDao.delete(userPref.getUsername());
        UserPreferences entity = userPreferencesDao.findOne(userPref.getUsername());
        assertNull(entity);
    }
}
