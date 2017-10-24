package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;

public class UserPreferencesTranslator {

    public static UserPreferencesDto toDto(UserPreferences userPreferences) {
        if (userPreferences == null) {
            throw new IllegalArgumentException("please, give me a preference!");
        }

        UserPreferencesDto dto = new UserPreferencesDto();
        dto.setUsername(userPreferences.getUsername());
        dto.setTypeId(userPreferences.getTypeId());

        return dto;
    }

    public static UserPreferences toEntity(UserPreferencesDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Please, give me a preference");
        }

        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setUsername(dto.getUsername());
        userPreferences.setTypeId(dto.getTypeId());

        return userPreferences;
    }
}
