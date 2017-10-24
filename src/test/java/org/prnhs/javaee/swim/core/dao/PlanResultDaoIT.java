package org.prnhs.javaee.swim.core.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.PlanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Created by Durim Kryeziu on Dec 07, 2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlanResultDaoIT {
    private static final Logger logger = LoggerFactory.getLogger(PlanResultDaoIT.class);

    @Autowired
    private PlanResultDao dao;

    @PersistenceContext
    private EntityManager entityManager;

    public static final Integer STROKES = 100;
    public static final Integer LENGTH = 50;
    public static final Time SWIM_TIME = Time.valueOf(LocalTime.now());

    private PlanResult planResult;

    @Before
    public void onStartUp() {
        planResult = new PlanResult();
        planResult.setId(5);
        planResult.setStrokes(STROKES);
        planResult.setLength(LENGTH);
        planResult.setSwimTime(SWIM_TIME);
    }

    @Test
    public void testSave() {
        dao.save(planResult);
        assertNotNull(planResult.getStrokes());
    }

    @Test
    public void testFindOne() {

        dao.save(planResult);
        entityManager.flush();
        assertNotNull(planResult.getLength());

        PlanResult savedEntity = dao.findOne(planResult.getId());

        assertNotNull(savedEntity);
        assertEquals(planResult, savedEntity);
    }

    @Test
    public void testFindAll() {
        dao.save(planResult);
        Iterable<PlanResult> entities = dao.findAll();
        assertTrue(entities.iterator().hasNext());
    }

    @Test
    public void testUpdate() {
        dao.save(planResult);
        entityManager.flush();
        assertNotNull(planResult.getSwimTime());

        PlanResult savedEntity = dao.findOne(planResult.getId());
        assertEquals(planResult, savedEntity);

        savedEntity.setStrokes(299);

        PlanResult updatedEntity = dao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }

    @Test
    public void testDelete() {

        dao.save(planResult);
        entityManager.flush();
        assertNotNull(planResult.getStrokes());
        dao.delete(planResult.getId());
        PlanResult entity = dao.findOne(planResult.getId());
        assertNull(entity);
    }
}