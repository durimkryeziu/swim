package org.prnhs.javaee.swim.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by Durim Kryeziu on Dec 07, 2016.
 */
@Entity(name = "plan_results")
public class PlanResult {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer strokes;
    private Integer length;
    private Time swimTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStrokes() {
        return strokes;
    }

    public void setStrokes(Integer strokes) {
        this.strokes = strokes;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Time getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(Time swimTime) {
        this.swimTime = swimTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanResult that = (PlanResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (strokes != null ? !strokes.equals(that.strokes) : that.strokes != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        return swimTime != null ? swimTime.equals(that.swimTime) : that.swimTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (strokes != null ? strokes.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (swimTime != null ? swimTime.hashCode() : 0);
        return result;
    }
}