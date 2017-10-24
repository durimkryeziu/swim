package org.prnhs.javaee.swim.dto;

import java.util.List;
import org.prnhs.javaee.swim.core.entity.User;

public class MyObjectB {
    
    private Integer myId;
    private String myIntv;
    private String password;
    private List<String> myList;
    private List<User> myUsers;

    public Integer getMyId() {
        return myId;
    }

    public void setMyId(Integer myId) {
        this.myId = myId;
    }

    public String getMyIntv() {
        return myIntv;
    }

    public void setMyIntv(String myIntv) {
        this.myIntv = myIntv;
    }

    public List<String> getMyList() {
        return myList;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getMyUsers() {
        return myUsers;
    }

    public void setMyUsers(List<User> myUsers) {
        this.myUsers = myUsers;
    }
}
