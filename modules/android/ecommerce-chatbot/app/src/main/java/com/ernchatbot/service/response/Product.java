package com.ernchatbot.service.response;

public class Product {
    Long idProduct;
    Long idCategory;
    String namaProduct;
    String deskripsiProduct;
    double hargaProduct;
    Long idPenjual;
    String imageLink;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public String getDeskripsiProduct() {
        return deskripsiProduct;
    }

    public void setDeskripsiProduct(String deskripsiProduct) {
        this.deskripsiProduct = deskripsiProduct;
    }

    public double getHargaProduct() {
        return hargaProduct;
    }

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
}
