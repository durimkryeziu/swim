package org.prnhs.javaee.swim.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.User;
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
public class UserDaoIT {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoIT.class);

    @Autowired
    private UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    private static final String USER_NAME = "some name";
    private static final String PASSWORD = "foopoo";

    private User p;

    @Before
    public void onStartUp() {
        p = new User();
        p.setUsername(USER_NAME);
        p.setPassword(PASSWORD);
        p.setEnabled(Boolean.TRUE);
    }

    @Test
    public void testSave() {
        userDao.save(p);
        assertNotNull(p.getUsername());
    }

    @Test(expected = JpaSystemException.class)
    public void testSave_NullUsername() {
        p.setUsername(null);
        userDao.save(p);
    }

    @Test
    public void testFindOne() {
        userDao.save(p);
        entityManager.flush();
        assertNotNull(p.getUsername());

        User savedEntity = userDao.findOne(p.getUsername());

        assertNotNull(savedEntity);
        assertEquals(p, savedEntity);
    }

    @Test
    public void testFindAll() {
        userDao.save(p);
        Iterable<User> entities = userDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }

    @Test
    public void testUpdate() {
        userDao.save(p);
        entityManager.flush();
        assertNotNull(p.getUsername());
        User savedEntity = userDao.findOne(p.getUsername());
        assertEquals(p, savedEntity);

        savedEntity.setUsername("another name");

        User updatedEntity = userDao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }

    @Test
    public void testDelete() {

        userDao.save(p);
        entityManager.flush();
        assertNotNull(p.getUsername());
        userDao.delete(p.getUsername());
        User entity = userDao.findOne(p.getUsername());
        assertNull(entity);
    }
}
