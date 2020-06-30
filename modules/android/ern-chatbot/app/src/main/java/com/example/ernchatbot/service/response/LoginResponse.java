package com.example.ernchatbot.service.response;

import com.fasterxml.jackson.annotation.JsonSetter;

public class LoginResponse {

    Long idUser;
    String email;
    String userName;
    String password;
    LoginEntities entities;

    @JsonSetter("idUser")
    public Long getIdUser() {return idUser; }

    public void setIdUser(Long idUser) {this.idUser = idUser; }

    @JsonSetter("email")
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email; }

    @JsonSetter("userName")
    public String getUserName() {return userName; }

    public void setUserName(String userName) {this.userName = userName;}

    @JsonSetter("password")
    public String getPassword() {return password; }

    public void setPassword(String password) {this.password = password; }

    @JsonSetter("entities")
    public LoginEntities getEntities() {return entities; }

    public void setEntities(LoginEntities entities) {this.entities = entities;}
}

