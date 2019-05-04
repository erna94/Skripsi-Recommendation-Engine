package com.example.ernchatbot.service.response;

import com.fasterxml.jackson.annotation.JsonSetter;


public class WitEntities {
    WitEntity[] keywordCategory;
    WitEntity[] subCategoryProduct;
    WitEntity[] categoryProduct;
    WitEntity[] intent;

    public WitEntity[] getKeywordCategory() {
        return keywordCategory;
    }

    @JsonSetter("keyword_category")
    public void setKeywordCategory(WitEntity[] keywordCategory) {
        this.keywordCategory = keywordCategory;
    }

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