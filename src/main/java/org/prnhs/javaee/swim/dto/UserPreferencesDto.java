package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.hateoas.ResourceSupport;

@ApiModel(value = "UserPreferencesDto", description = "Model for representing a UserPreference")
public class UserPreferencesDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1254941322074092446L;

    @NotNull(message = "{user.preference.username.null}")
    @Size(max = 45, min = 1, message = "{user.preference.username.size}")
    @ApiModelProperty(value = "UserPreferences' username")
    private String username;
    @ApiModelProperty(value = "UserPreferences' typeId")
    private Integer typeId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "UserPreferencesDto{" + "username=" + username + ", typeId=" + typeId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.username);
        hash = 17 * hash + Objects.hashCode(this.typeId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPreferencesDto other = (UserPreferencesDto) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.typeId, other.typeId)) {
            return false;
        }
        return true;
    }

}
