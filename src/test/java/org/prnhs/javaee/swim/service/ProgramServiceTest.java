package org.prnhs.javaee.swim.service;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.prnhs.javaee.swim.core.dao.ProgramDao;
import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.prnhs.javaee.swim.services.ProgramService;


public class ProgramServiceTest {
    
    private static final String OBJECTIVE = "the objective";
    private static final int ID = 2;
    
    @InjectMocks
    private ProgramService uut;
    
    @Mock
    private ProgramDao dao;
    
    @Mock
    private Mapper mapper;
    
    private Program program;
    private List<Program> programs;
    private ProgramDto dto;
    private List<ProgramDto> dtos;
    
    @Before
    public void onStartUp(){
        //assertNotNull(mapper);
        MockitoAnnotations.initMocks(this);
        
        program = new Program();
        program.setObjective(OBJECTIVE);
        
        dto = new ProgramDto();
        dto.setKey(ID);
        dto.setObjective(OBJECTIVE);
        programs = new ArrayList<>();
        dtos = new ArrayList<>();
    }
    
    @Test
    public void test_saveWithFound(){
        when(dao.findOne(Matchers.anyInt())).thenReturn(program);
        when(dao.save(program)).thenReturn(program);
        when(mapper.map(dto, Program.class)).thenReturn(program);
                
        ProgramDto savedDto = uut.save(dto);
        Mockito.verify(dao).save(program);
    }
    
    @Test
    public void test_saveWithoutFound(){
        when(dao.findOne(Matchers.anyInt())).thenReturn(null);
        when(dao.save(program)).thenReturn(program);
        when(mapper.map(dto, Program.class)).thenReturn(program);
        
        ProgramDto savedDto = uut.save(dto);
        Mockito.verify(dao).save(program);        
    }
    
    @Test
    public void test_getById(){
        when(dao.findOne(ID)).thenReturn(program);
        when(mapper.map(dto, Program.class)).thenReturn(program);
        
        ProgramDto dto = uut.getById(ID);
        Mockito.verify(dao).findOne(ID);  
    }
    
    @Test
    public void test_getByIdNotFound(){
        when(dao.findOne(ID)).thenReturn(null);
        
        ProgramDto dto = uut.getById(ID);
        Mockito.verify(dao).findOne(ID);  
    }
    
    @Test
    public void test_getAll(){
        when(dao.findAll()).thenReturn(programs);
        
        dtos = uut.getAll();
        Mockito.verify(dao).findAll(); 
    }
    
    @Test
    public void test_getAllNotFound(){
        when(dao.findAll()).thenReturn(null);
        
        dtos = uut.getAll();
        Mockito.verify(dao).findAll(); 
    }
    
    @Test
    public void test_delete(){
        when(dao.findOne(ID)).thenReturn(program);
        
        uut.delete(ID);
        Mockito.verify(dao).delete(program);
    }
    
    
}
