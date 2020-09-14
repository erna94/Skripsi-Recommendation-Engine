package com.ernchatbot.service.response;

public class UserResponse {

    Long idUser;
    String lokasi;
    String pekerjaan;
    Long umur;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Long getUmur() {
        return umur;
    }

    public void setUmur(Long umur) {
        this.umur = umur;
    }
}
