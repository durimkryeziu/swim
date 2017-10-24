package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dozer.Mapper;
import org.prnhs.javaee.swim.core.dao.ProgramDao;
import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Program service where all operations on a program are preformed.
 * 
 * @author mirlind
 */
@Service
public class ProgramService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramService.class);
    
    @Autowired
    private ProgramDao dao;
    
    @Autowired
    private Mapper mapper;
    
    public ProgramDto save(ProgramDto programDto){        
        Program program = mapper.map(programDto, Program.class);        
        program = dao.save(program);
        
        ProgramDto savedProgram = mapper.map(program, ProgramDto.class);
        LOGGER.debug("Save method executed successfully in ProgramService, the program {} got saved", savedProgram);
        return savedProgram;
    }
    
    public ProgramDto getById(Integer id){
        ProgramDto dto = null;
        Program p = dao.findOne(id);
        LOGGER.debug("getById method called in ProgramService, the given id was: {}", id);
        
        if(p != null){
            LOGGER.debug("getById called: A program was found: {}", p);
            dto = mapper.map(p, ProgramDto.class);
        }
        return dto;
    }
    
    public List<ProgramDto> getAll(){
        Iterable<Program> programs = dao.findAll();
        List<ProgramDto> dtos = new ArrayList<>();
        LOGGER.debug("getAll method called in ProgramService");
        
        if(programs!=null){
            Iterator<Program> iterator = programs.iterator();
            int count=0;    
            while(iterator.hasNext()){
                ProgramDto dto = mapper.map(iterator.next(), ProgramDto.class);
                dtos.add(dto);
                count++;
            }
            LOGGER.debug("getAll method called in ProgramService and there were found {} programs", count);
        }
            
        return dtos;        
    }
    
    public void delete(Integer id){
        Program p = dao.findOne(id);
        LOGGER.debug("delete method called in ProgramService with the given parameter(id): {}", id);
        
        if(p != null){
            dao.delete(p);
            LOGGER.debug("A program was found and it was deleted successfully, id: {}", id);
        }
    }
}
