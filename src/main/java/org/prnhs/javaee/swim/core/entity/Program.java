
package org.prnhs.javaee.swim.core.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;   
import javax.persistence.Id;

@Entity(name="programs")
public class Program {
    
    @Id 
    @GeneratedValue
    private int id;
    private String objective;
    
    public Program(){}

    public int getId() {
        return id;
    }
    
    public void setObjective(String o){
        objective =o;
    }
    public String getObjective(){
        return objective;
    }

    @Override
    public String toString() {
        return "Program{" + "id=" + id + ", objective=" + objective + '}';
    }    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.objective);
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
        final Program other = (Program) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.objective, other.objective)) {
            return false;
        }
        return true;
    }
}
