package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dozer.Mapper;
import org.prnhs.javaee.swim.core.dao.UserDao;
import org.prnhs.javaee.swim.core.entity.User;
import org.prnhs.javaee.swim.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service where all operations on a user are preformed.
 *
 * @author mosgjig
 */
@Service
public class UserService {

    @Autowired
    private UserDao dao;
    @Autowired
    private Mapper mapper;

    /**
     * Save the user regardless if it exists or not.
     *
     * @param userDto - a {@link UserDto}
     * @return userDto - the save user usually contains a populated key if none
     * was fed
     */
    public UserDto save(UserDto userDto) {
        User user = dao.findOne(userDto.getUsername());

        if (user == null) {
            user = mapper.map(userDto, User.class);
        } else {
            mapper.map(userDto, user);
        }

        user = dao.save(user);

        UserDto savedUser = mapper.map(user, UserDto.class);

        return savedUser;
    }

    public UserDto getById(String id) {
        UserDto dto = null;
        User user = dao.findOne(id);
        if (user != null) {
            dto = mapper.map(user, UserDto.class);
        }
        return dto;
    }

    public List<UserDto> getAll() {

        Iterable<User> users = dao.findAll();
        Iterator<User> it = users.iterator();
        List<UserDto> dtos = new ArrayList<>();
        while (it.hasNext()) {
            User u = it.next();
            UserDto dto = mapper.map(u, UserDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    public void delete(String id) {
        User user = dao.findOne(id);
        if (user != null) {
            user.setEnabled(Boolean.FALSE);
            dao.save(user);
        }
    }
}
