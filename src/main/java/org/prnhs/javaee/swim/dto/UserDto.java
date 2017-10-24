package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.prnhs.javaee.swim.services.ValidateUser;
import org.springframework.hateoas.ResourceSupport;

@ApiModel(value = "UserDto", description = "Model for representing a User")
@ValidateUser
public class UserDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1254941322074092446L;

    @NotNull(message = "{user.username.null}")
    @Size(max = 45, min = 1, message = "{user.username.size}")
    @ApiModelProperty(value = "User's key/id")
    private String username;
    @NotNull(message = "{user.password.null}")
    @Size(max = 45, min = 1, message = "{user.password.size}")
    @ApiModelProperty(value = "User's password")
    private String password;
    @NotNull(message = "{user.enabled.null}")
    @ApiModelProperty(value = "Is the user enabled or active")
    private Boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.enabled);
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
        final UserDto other = (UserDto) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDto{" + "username=" + username + ", password=" + password + ", enabled=" + enabled + '}';
    }
}
