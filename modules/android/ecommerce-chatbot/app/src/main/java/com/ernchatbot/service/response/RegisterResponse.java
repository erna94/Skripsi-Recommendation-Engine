package com.ernchatbot.service.response;


import com.fasterxml.jackson.annotation.JsonSetter;

public class RegisterResponse {
    Long idUser;
    String userName;
    String email;
    String password;
    String umur;
    String lokasi;
    String pekerjaan;

    @JsonSetter("idUser")
    public Long getIdUser() { return idUser; }

    public void setIdUser(Long idUser) { this.idUser = idUser; }

    @JsonSetter("userName")
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @JsonSetter("email")
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @JsonSetter("password")
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @JsonSetter("umur")
    public String getUmur() { return umur; }

    public void setUmur(String umur) { this.umur = umur; }

    @JsonSetter("lokasi")
    public String getLokasi() { return lokasi; }

    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    @JsonSetter("pekerjaan")
    public String getPekerjaan() { return pekerjaan; }

    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }
}
