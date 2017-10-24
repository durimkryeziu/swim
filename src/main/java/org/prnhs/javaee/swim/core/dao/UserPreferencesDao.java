package org.prnhs.javaee.swim.core.dao;

import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.springframework.data.repository.CrudRepository;

public interface UserPreferencesDao extends CrudRepository<UserPreferences, String> {

}
