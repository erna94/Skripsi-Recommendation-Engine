package com.ernchatbot.ui;


public class ProductInfo {
    String imageLink;
    String itemName;
    Double itemPrice;
    String itemDescription;
    String itemId;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return itemId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProductInfo) {
            ProductInfo toCompare = (ProductInfo)obj;
            return toCompare.itemId.equals(this.itemId);
        } else {
            return false;
        }
    }
}
