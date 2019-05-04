package com.example.ernchatbot.service.response;

import com.fasterxml.jackson.annotation.JsonSetter;

public class WitEntities {
    WitEntity[] subCategoryProduct;
    WitEntity[] categoryProduct;
    WitEntity[] intent;

    public WitEntity[] getSubCategoryProduct() {
        return subCategoryProduct;
    }

    @JsonSetter("sub_category_produk")
    public void setSubCategoryProduct(WitEntity[] subCategoryProduct) {
        this.subCategoryProduct = subCategoryProduct;
    }

    public WitEntity[] getCategoryProduct() {
        return categoryProduct;
    }

    @JsonSetter("category_produk")
    public void setCategoryProduct(WitEntity[] categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public WitEntity[] getIntent() {
        return intent;
    }

    public void setIntent(WitEntity[] intent) {
        this.intent = intent;
    }
}
