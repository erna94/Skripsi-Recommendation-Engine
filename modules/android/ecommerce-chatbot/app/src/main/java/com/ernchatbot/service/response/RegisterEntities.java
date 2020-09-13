package com.ernchatbot.service.response;

public class RegisterEntities {
    Long idUser;
    String userName;
    String email;
    String password;
    String umur;
    String lokasi;
    String pekerjaan;


    public Long getIdUser() { return idUser; }

    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUmur() { return umur; }

    public void setUmur(String umur) { this.umur = umur; }

    public String getLokasi() { return lokasi; }

    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public String getPekerjaan() { return pekerjaan; }

    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }
}
