package org.prnhs.javaee.swim.dto;

import java.util.List;

public class MyObjectA {

    private Integer myKey;
    private Integer myIntv;
    private String password;
    private List<String> myList;
    private List<UserDto> myUsers;

    public Integer getMyKey() {
        return myKey;
    }

    public void setMyKey(Integer myKey) {
        this.myKey = myKey;
    }

    public Integer getMyIntv() {
        return myIntv;
    }

    public void setMyIntv(Integer myIntv) {
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

    public List<UserDto> getMyUsers() {
        return myUsers;
    }

    public void setMyUsers(List<UserDto> myUsers) {
        this.myUsers = myUsers;
    }

}
