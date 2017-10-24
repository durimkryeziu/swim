package org.prnhs.javaee.swim.service;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.prnhs.javaee.swim.core.dao.UserPreferencesDao;
import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.prnhs.javaee.swim.services.UserPreferencesService;
import static org.mockito.Mockito.when;

public class UserPreferencesServiceTest {

    private static final String USERNAME = "Argita";
    private static final Integer TYPEID = 1;

    @InjectMocks
    private UserPreferencesService uut;

    @Mock
    private UserPreferencesDao dao;

    private UserPreferences userPreference;
    private List<UserPreferences> userPreferences;
    private UserPreferencesDto dto;
    private List<UserPreferencesDto> dtos;

    @Before
    public void onStartUp() {

        MockitoAnnotations.initMocks(this);
        userPreference = new UserPreferences();
        userPreference.setUsername(USERNAME);
        userPreference.setTypeId(TYPEID);

        dto = new UserPreferencesDto();
        dto.setUsername(USERNAME);
        dto.setTypeId(TYPEID);
    }

    @Test
    public void test_saveWithFound() {
        when(dao.findOne(Matchers.anyString())).thenReturn(userPreference);
        when(dao.save(userPreference)).thenReturn(userPreference);

        UserPreferencesDto savedDto = uut.save(dto);
        Mockito.verify(dao).save(userPreference);
    }

    @Test
    public void test_saveNotFound() {
        when(dao.findOne(Matchers.anyString())).thenReturn(null);
        when(dao.save(userPreference)).thenReturn(userPreference);

        UserPreferencesDto savedDto = uut.save(dto);
        Mockito.verify(dao).save(userPreference);

    }

    @Test
    public void test_getByUsername() {
        when(dao.findOne(USERNAME)).thenReturn(userPreference);

        dto = uut.getByUsername(USERNAME);
        Mockito.verify(dao).findOne(USERNAME);

    }

    @Test
    public void test_getByUsernameNotFound() {
        when(dao.findOne(USERNAME)).thenReturn(null);

        dto = uut.getByUsername(USERNAME);
        Mockito.verify(dao).findOne(USERNAME);

    }

    @Test
    public void test_getAll() {
        when(dao.findAll()).thenReturn(userPreferences);

        dtos = uut.getAll();
        Mockito.verify(dao).findAll();
    }

    @Test
    public void test_getAllFailed() {
        when(dao.findAll()).thenReturn(null);

        dtos = uut.getAll();
        Mockito.verify(dao).findAll();

    }

    @Test
    public void test_delete() {
        when(dao.findOne(USERNAME)).thenReturn(userPreference);

        uut.delete(USERNAME);
        Mockito.verify(dao).delete(userPreference);

    }
}
