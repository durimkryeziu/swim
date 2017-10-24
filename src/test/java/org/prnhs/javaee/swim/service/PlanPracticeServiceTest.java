package org.prnhs.javaee.swim.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.prnhs.javaee.swim.core.dao.PlanPracticeDao;
import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;
import org.prnhs.javaee.swim.services.PlanPracticeService;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */

public class PlanPracticeServiceTest {

    private static final Integer ID = 7;
    private static final Integer LENGTH = 50;
    private static final Integer MULTIPLE = 5;
    private static final String EXERCISE = "The Freestyle Stroke";
    private static final Time SPLIT = Time.valueOf(LocalTime.now());

    @InjectMocks
    private PlanPracticeService uut;

    @Mock
    private PlanPracticeDao dao;

    private PlanPractice planPractice;
    private List<PlanPractice> planPractices;

    private PlanPracticeDto planPracticeDto;
    private List<PlanPracticeDto> planPracticeDtos;

    @Before
    public void onStartUp() {
        MockitoAnnotations.initMocks(this);

        planPractice = new PlanPractice();
        planPractice.setId(ID);
        planPractice.setLength(LENGTH);
        planPractice.setMultiple(MULTIPLE);
        planPractice.setExercise(EXERCISE);
        planPractice.setSplit(SPLIT);

        planPracticeDto = new PlanPracticeDto();
        planPracticeDto.setKey(ID);
        planPracticeDto.setLength(LENGTH);
        planPracticeDto.setMultiple(MULTIPLE);
        planPracticeDto.setExercise(EXERCISE);
        planPracticeDto.setSplit(SPLIT);
    }

    @Test
    public void testSaveWithFound() {
        when(dao.findOne(Matchers.anyInt())).thenReturn(planPractice);
        when(dao.save(planPractice)).thenReturn(planPractice);

        PlanPracticeDto savedDto = uut.save(planPracticeDto);

        Mockito.verify(dao).save(planPractice);
    }
}