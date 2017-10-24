package org.prnhs.javaee.swim.core.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
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
public class PlanPracticeDaoIT {
    private static final Logger logger = LoggerFactory.getLogger(PlanPracticeDaoIT.class);

    @Autowired
    private PlanPracticeDao dao;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Integer LENGTH = 50;
    private static final Integer MULTIPLE = 5;
    private static final String EXERCISE = "The Freestyle Stroke";
    private static final Time SPLIT = Time.valueOf(LocalTime.now());

    private PlanPractice planPractice;

    @Before
    public void onStartUp() {
        planPractice = new PlanPractice();
        planPractice.setId(6);
        planPractice.setLength(LENGTH);
        planPractice.setMultiple(MULTIPLE);
        planPractice.setExercise(EXERCISE);
        planPractice.setSplit(SPLIT);
    }

    @Test
    public void testSave() {
        dao.save(planPractice);
        assertNotNull(planPractice.getExercise());
    }

    @Test
    public void testFindOne() {

        dao.save(planPractice);
        entityManager.flush();
        assertNotNull(planPractice.getExercise());

        PlanPractice savedEntity = dao.findOne(planPractice.getId());

        assertNotNull(savedEntity);
        assertEquals(planPractice, savedEntity);
    }

    @Test
    public void testFindAll() {
        dao.save(planPractice);
        Iterable<PlanPractice> entities = dao.findAll();
        assertTrue(entities.iterator().hasNext());
    }

    @Test
    public void testUpdate() {
        dao.save(planPractice);
        entityManager.flush();
        assertNotNull(planPractice.getExercise());

        PlanPractice savedEntity = dao.findOne(planPractice.getId());
        assertEquals(planPractice, savedEntity);

        savedEntity.setExercise("Another Exercise");

        PlanPractice updatedEntity = dao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }

    @Test
    public void testDelete() {

        dao.save(planPractice);
        entityManager.flush();
        assertNotNull(planPractice.getExercise());
        dao.delete(planPractice.getId());
        PlanPractice entity = dao.findOne(planPractice.getId());
        assertNull(entity);
    }
}