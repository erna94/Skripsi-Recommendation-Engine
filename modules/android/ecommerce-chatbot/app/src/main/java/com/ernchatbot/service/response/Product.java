package com.ernchatbot.service.response;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Product {
    Long idProduct;
    Long idCategory;
    String namaProduct;
    String deskripsiProduct;
    double hargaProduct;
    Long idPenjual;
    String imageLink;
    String brand;
    String warna;
    String bahan;

    public Long getIdProduct() {
        return idProduct;
    }

    @JsonSetter("idProduk")
    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    @JsonSetter("idKategori")
    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    @JsonSetter("namaProduk")
    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public String getDeskripsiProduct() {
        return deskripsiProduct;
    }

    @JsonSetter("deskripsiProduk")
    public void setDeskripsiProduct(String deskripsiProduct) {
        this.deskripsiProduct = deskripsiProduct;
    }

    public double getHargaProduct() {
        return hargaProduct;
    }

    @JsonSetter("hargaProduk")
    public void setHargaProduct(double hargaProduct) {
        this.hargaProduct = hargaProduct;
    }

    public Long getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(Long idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }
}
