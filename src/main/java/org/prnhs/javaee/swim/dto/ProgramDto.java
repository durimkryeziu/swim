package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.prnhs.javaee.swim.services.ValidateProgram;
import org.springframework.hateoas.ResourceSupport;

@ApiModel(value="ProgramDto", description="Model for representing a Program")
@ValidateProgram
public class ProgramDto extends ResourceSupport implements Serializable{
    
    @ApiModelProperty(value = "Program's key/id", readOnly = true)
    private Integer key;
    @NotNull(message = "{program.objective.null}")
    @Size(max = 300, min = 1, message = "{program.objective.size}")
    @ApiModelProperty(value = "Program's objective", required = true)
    private String objective;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getObjective() {
        return objective;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.key);
        hash = 23 * hash + Objects.hashCode(this.objective);
        return hash;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    

    @Override
    public String toString() {
        return "ProgramDto{" + "id=" + key + ", objective=" + objective + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramDto other = (ProgramDto) obj;
        if (this.key != other.key) {
            return false;
        }
        if (!Objects.equals(this.objective, other.objective)) {
            return false;
        }
        return true;
    }    
    
}
