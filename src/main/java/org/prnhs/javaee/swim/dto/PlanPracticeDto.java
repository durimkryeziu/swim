package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@ApiModel(value = "PlanPracticeDto", description = "Model for representing a Plan Practice")
public class PlanPracticeDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -1466114511747372377L;

    @NotNull(message = "{plan.practice.key.null}")
    @ApiModelProperty("Plan Practice's key")
    private Integer key;
    @ApiModelProperty("Plan Practice's length")
    private Integer length;
    @ApiModelProperty("Plan Practice's multiple")
    private Integer multiple;
    @Size(min = 1, max = 45, message = "{plan.practice.exercise.size}")
    @ApiModelProperty("Plan Practice's exercise")
    private String exercise;
    @ApiModelProperty("Plan Practice's split")
    private Time split;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

        PlanPracticeDto that = (PlanPracticeDto) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (multiple != null ? !multiple.equals(that.multiple) : that.multiple != null) return false;
        if (exercise != null ? !exercise.equals(that.exercise) : that.exercise != null) return false;
        return split != null ? split.equals(that.split) : that.split == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (multiple != null ? multiple.hashCode() : 0);
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        result = 31 * result + (split != null ? split.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlanPracticeDto { " +
                "key = " + key +
                ", length = " + length +
                ", multiple = " + multiple +
                ", exercise = '" + exercise + '\'' +
                ", split = " + split +
                " }";
    }
}