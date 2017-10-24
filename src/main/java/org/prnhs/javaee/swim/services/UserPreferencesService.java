package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.UserPreferencesDao;
import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserPreferencesService.class);
    @Autowired
    private UserPreferencesDao dao;

    public UserPreferencesDto save(UserPreferencesDto userPreferencesDto) {
        LOGGER.debug("Save method is called ");

        if (userPreferencesDto == null) {
            LOGGER.warn("Nothing to save because you left the username blank");
            throw new IllegalArgumentException("Please, give me a preference");
        }

        UserPreferences userPreferences = dao.findOne(userPreferencesDto.getUsername());

        if (userPreferences == null) {
            userPreferences = UserPreferencesTranslator.toEntity(userPreferencesDto);

        } else {
            userPreferences.setUsername(userPreferencesDto.getUsername());
            userPreferences.setTypeId(userPreferencesDto.getTypeId());
        }

        userPreferences = dao.save(userPreferences);

        UserPreferencesDto savedUserPreferences = UserPreferencesTranslator.toDto(userPreferences);

        LOGGER.debug("UserPreference {} is already saved", savedUserPreferences);
        return savedUserPreferences;

    }

    public UserPreferencesDto getByUsername(String username) {
        LOGGER.debug("getByUsermane method is called ");

        UserPreferencesDto dto = null;
        UserPreferences up = dao.findOne(username);

        if (up != null) {
            dto = UserPreferencesTranslator.toDto(up);
            LOGGER.debug("{} was found", up);
        }

        return dto;
    }

    public List<UserPreferencesDto> getAll() {
        LOGGER.debug("getAll method is called ");

        Iterable<UserPreferences> userPreferences = dao.findAll();
        List<UserPreferencesDto> dtos = new ArrayList<>();

        if (userPreferences != null) {
            Iterator<UserPreferences> it = userPreferences.iterator();

            int count = 0;
            while (it.hasNext()) {
                UserPreferences up = it.next();
                UserPreferencesDto dto = UserPreferencesTranslator.toDto(up);
                dtos.add(dto);
                count++;
            }
            LOGGER.debug("The number of userPreferences that were found is: {}", count);
        }
        return dtos;
    }

    public void delete(String username) {
        LOGGER.debug("delete method is called");

        UserPreferences userPreferences = dao.findOne(username);

        if (userPreferences != null) {
            dao.delete(userPreferences);
            LOGGER.debug("Method Delete has successfully deleted {} UserPreference", username);
        }

    }

}
