package com.kainos.ea.model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

    protected int id; // employee number
    protected String username;
    protected String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @JsonCreator
    public UserRequest(
            @JsonProperty("username") String username,
            @JsonProperty("pwd") String pwd
            ) {
        this.setUsername(username);
        this.setPwd(pwd);
    }


}
