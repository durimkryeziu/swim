package org.prnhs.javaee.swim.core.dao;

import org.prnhs.javaee.swim.core.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String>{
    
}
