package org.prnhs.javaee.swim.service;

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
import org.prnhs.javaee.swim.core.dao.UserDao;
import org.prnhs.javaee.swim.core.entity.User;
import org.prnhs.javaee.swim.dto.UserDto;
import org.prnhs.javaee.swim.services.UserService;

public class UserServiceTest {

    private static final String USERNAME = "modest";
    private static final String PASSWORD = "pwdd";
    private static final Boolean ENABLED = Boolean.TRUE;

    @InjectMocks
    private UserService uut;

    @Mock
    private UserDao dao;
    @Mock
    private Mapper mapper;

    private User user;
    private List<User> users;
    private UserDto dto;
    private List<UserDto> dtos;

    @Before
    public void onStartUp() {

        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setEnabled(ENABLED);
        
        dto = new UserDto();
        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setEnabled(ENABLED);
    }
    
    @Test
    public void test_saveWithFound(){
        when(mapper.map(dto, User.class)).thenReturn(user);
        when(mapper.map(user, UserDto.class)).thenReturn(dto);

        when(dao.findOne(Matchers.anyString())).thenReturn(user);
        when(dao.save(user)).thenReturn(user);
        
        UserDto savedDto = uut.save(dto);
        
        Mockito.verify(dao).save(user);
    }
}
