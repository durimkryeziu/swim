package org.prnhs.javaee.swim.core.dao;

import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Durim Kryeziu on Dec 07, 2016.
 */
public interface PlanPracticeDao extends CrudRepository<PlanPractice, Integer> {

    PlanPractice findByLength(Integer length);
}