package org.prnhs.javaee.swim.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by Durim Kryeziu on Dec 07, 2016.
 */
@Entity(name = "plan_practices")
public class PlanPractice {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer length;
    private Integer multiple;
    private String exercise;
    private Time split;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Time getSplit() {
        return split;
    }

    public void setSplit(Time split) {
        this.split = split;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanPractice that = (PlanPractice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (multiple != null ? !multiple.equals(that.multiple) : that.multiple != null) return false;
        if (exercise != null ? !exercise.equals(that.exercise) : that.exercise != null) return false;
        return split != null ? split.equals(that.split) : that.split == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (multiple != null ? multiple.hashCode() : 0);
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        result = 31 * result + (split != null ? split.hashCode() : 0);
        return result;
    }
}