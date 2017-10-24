package org.prnhs.javaee.swim.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.Program;
import org.slf4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProgramDaoIT {
    private static final Logger logger = LoggerFactory.getLogger(ProgramDaoIT.class);
       
    @Autowired
    private ProgramDao programDao;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String OBJECTIVE = "Some objective";

    private Program p;
    
    @Before
    public void onStartUp(){
        p = new Program();
        p.setObjective(OBJECTIVE);    
    }
    
    @Test
    public void testSave() {
        programDao.save(p);
        assertNotNull(p.getId());
        assertNotNull(p.getObjective());
    }
    
//    @Test(expected = JpaSystemException.class)
//    public void testSave_NullObjective() {
//        p.setObjective(null);
//        programDao.save(p);
//    }
    
    @Test
    public void testFindOne() {
        programDao.save(p);
        entityManager.flush();
        assertNotNull("Expected id", p.getId());
        
        Program savedEntity = programDao.findOne(p.getId());

        assertNotNull(savedEntity);
        assertEquals(p, savedEntity);
    }
    
    @Test
    public void testFindAll() {
        programDao.save(p);
        Iterable<Program> entities = programDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }
    
    @Test
    public void testUpdate() {
        programDao.save(p);
        entityManager.flush();
        assertNotNull(p.getId());
        Program savedEntity = programDao.findOne(p.getId());
        assertEquals(p, savedEntity);

        savedEntity.setObjective("another objective");

        Program updatedEntity = programDao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }
    
    @Test
    public void testDelete() {
        programDao.save(p);
        entityManager.flush();
        assertNotNull(p.getId());
        programDao.delete(p.getId());
        Program entity = programDao.findOne(p.getId());
        assertNull(entity);
    }
    
}
