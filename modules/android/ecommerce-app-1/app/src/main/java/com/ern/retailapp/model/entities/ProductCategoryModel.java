/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

/**
 *
 */
package com.ern.retailapp.model.entities;

/**
 * @author Hitesh
 */
public class ProductCategoryModel {

    private String categoryName;
    private String categoryDescription;
    private String categoryDiscount;
    private String categoryImageUrl;
    private Long categoryId;
    /**
     * @param productCategoryName
     * @param productCategoryDescription
     * @param productCategoryDiscount
     * @param productCategoryUrl
     */
    public ProductCategoryModel(String productCategoryName, String productCategoryDescription,
                                String productCategoryDiscount, String productCategoryUrl, Long productCategoryId) {
        super();
        this.categoryName = productCategoryName;
        this.categoryDescription = productCategoryDescription;
        this.categoryDiscount = productCategoryDiscount;
        this.categoryImageUrl = productCategoryUrl;
        this.categoryId = productCategoryId;
    }

    public ProductCategoryModel(String productCategoryName, String productCategoryDescription,
                                String productCategoryDiscount, String productCategoryUrl) {
        super();
        this.categoryName = productCategoryName;
        this.categoryDescription = productCategoryDescription;
        this.categoryDiscount = productCategoryDiscount;
        this.categoryImageUrl = productCategoryUrl;
    }


    /**
     * @return the idproductcategory
     */
    public String getProductCategoryName() {
        return categoryName;
    }

    /**
     * @param idproductcategory the idproductcategory to set
     */
    public void setProductCategoryName(String idproductcategory) {
        this.categoryName = idproductcategory;
    }

    /**
     * @return the productDescription
     */
    public String getProductCategoryDescription() {
        return categoryDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductCategoryDescription(String productDescription) {
        this.categoryDescription = productDescription;
    }

    /**
     * @return the productDiscount
     */
    public String getProductCategoryDiscount() {
        return categoryDiscount;
    }

    /**
     * @param productDiscount the productDiscount to set
     */
    public void setProductCategoryDiscount(String productDiscount) {
        this.categoryDiscount = productDiscount;
    }

    /**
     * @return the productUrl
     */
    public String getProductCategoryImageUrl() {
        return categoryImageUrl;
    }

    /**
     * @param productUrl the productUrl to set
     */
    public void setProductCategoryImageUrl(String productUrl) {
        this.categoryImageUrl = productUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
