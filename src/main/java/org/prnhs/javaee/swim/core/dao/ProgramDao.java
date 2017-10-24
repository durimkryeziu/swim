package org.prnhs.javaee.swim.core.dao;

import org.prnhs.javaee.swim.core.entity.Program;
import org.springframework.data.repository.CrudRepository;

public interface ProgramDao extends CrudRepository<Program, Integer>{   
    
}
