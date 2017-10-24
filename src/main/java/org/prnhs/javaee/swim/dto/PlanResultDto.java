package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@ApiModel(value = "PlanResultDto", description = "Model for representing a Plan Result")
public class PlanResultDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -3771894525549969986L;

    @NotNull(message = "{plan.result.key.null}")
    @ApiModelProperty("Plan Result's key")
    private Integer key;
    @ApiModelProperty("Plan Result's strokes")
    private Integer strokes;
    @ApiModelProperty("Plan Result's length")
    private Integer length;
    @ApiModelProperty("Plan Result's swim time")
    private Time swimTime;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

        PlanResultDto that = (PlanResultDto) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (strokes != null ? !strokes.equals(that.strokes) : that.strokes != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        return swimTime != null ? swimTime.equals(that.swimTime) : that.swimTime == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (strokes != null ? strokes.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (swimTime != null ? swimTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlanResultDto { " +
                "key = " + key +
                ", strokes = " + strokes +
                ", length = " + length +
                ", swimTime = " + swimTime +
                " }";
    }
}