package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@ApiModel(value = "ContactsDto", description = "Model for representing a Contact")
public class ContactsDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1254941322074092446L;

    @NotNull(message = "{contact.key.null}")
    @ApiModelProperty(value = "Contact's key/id")
    private Integer key;
    @NotNull(message = "{contact.firstName.null}")
    @Size(max = 45, min = 1, message = "{contact.firstName.size}")
    @ApiModelProperty(value = "Contact's fistname")
    private String firstName;
    @Size(max = 45, min = 1, message = "{contact.middleName.size}")
    @ApiModelProperty(value = "Contact's middlename")
    private String middleName;
    @NotNull(message = "{contact.lastName.null}")
    @Size(max = 45, min = 1, message = "{contact.lastName.size}")
    @ApiModelProperty(value = "Contact's lastname")
    private String lastName;
    @NotNull(message = "{contact.title.null}")
    @Size(max = 45, min = 1,message = "{contact.title.size}")
    @ApiModelProperty(value = "Contact's title")
    private String title;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ContactsDto{" +
                "id=" + key +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.key);
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.middleName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
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
        final ContactsDto other = (ContactsDto) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }
}
