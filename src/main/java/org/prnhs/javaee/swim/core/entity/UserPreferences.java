package org.prnhs.javaee.swim.core.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_preferences")
public class UserPreferences {

    @Id
    private String username;
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
        return "UserPreferences{" + "username=" + username + ", typeId=" + typeId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.typeId);
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
        final UserPreferences other = (UserPreferences) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.typeId, other.typeId)) {
            return false;
        }
        return true;
    }

}
