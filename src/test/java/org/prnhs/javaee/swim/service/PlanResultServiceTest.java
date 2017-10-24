package org.prnhs.javaee.swim.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.prnhs.javaee.swim.core.dao.PlanResultDao;
import org.prnhs.javaee.swim.core.entity.PlanResult;
import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.prnhs.javaee.swim.services.PlanResultService;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
public class PlanResultServiceTest {

    private static final Integer ID = 5;
    private static final Integer STROKES = 100;
    private static final Integer LENGTH = 50;
    private static final Time SWIM_TIME = Time.valueOf(LocalTime.now());

    @InjectMocks
    private PlanResultService uut;

    @Mock
    private PlanResultDao dao;

    private PlanResult planResult;
    private List<PlanResult> planResults;

    private PlanResultDto planResultDto;
    private List<PlanResultDto> planResultDtos;

    @Before
    public void onStartUp() {
        MockitoAnnotations.initMocks(this);

        planResult = new PlanResult();
        planResult.setId(ID);
        planResult.setStrokes(STROKES);
        planResult.setLength(LENGTH);
        planResult.setSwimTime(SWIM_TIME);

        planResultDto = new PlanResultDto();
        planResultDto.setKey(ID);
        planResultDto.setStrokes(STROKES);
        planResultDto.setLength(LENGTH);
        planResultDto.setSwimTime(SWIM_TIME);
    }

    @Test
    public void testSaveWithFound() {
        when(dao.findOne(Matchers.anyInt())).thenReturn(planResult);
        when(dao.save(planResult)).thenReturn(planResult);

        PlanResultDto savedDto = uut.save(planResultDto);

        Mockito.verify(dao).save(planResult);
    }

    @Test
    public void testSaveWithoutFound() {
        when(dao.findOne(Matchers.anyInt())).thenReturn(null);
        when(dao.save(planResult)).thenReturn(planResult);

        PlanResultDto savedDto = uut.save(planResultDto);

        assertNotNull(savedDto);
        assertEquals(planResultDto, savedDto);

        Mockito.verify(dao).save(planResult);
    }

    @Test
    public void testGetByIdWithFound() {
        when(dao.findOne(Matchers.anyInt())).thenReturn(planResult);

        PlanResultDto resultDto = uut.getById(Matchers.anyInt());
        assertEquals(planResultDto, resultDto);
    }

    @Test
    public void testGetByIdWithoutFound() {
        when(dao.findOne(Matchers.anyInt())).thenReturn(null);

        PlanResultDto resultDto = uut.getById(Matchers.anyInt());

        assertEquals(resultDto, null);
    }
}