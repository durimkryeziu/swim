package org.prnhs.javaee.swim.core.dao;

import org.prnhs.javaee.swim.core.entity.Contacts;
import org.springframework.data.repository.CrudRepository;

public interface ContactsDao extends CrudRepository<Contacts, Integer> {

}

