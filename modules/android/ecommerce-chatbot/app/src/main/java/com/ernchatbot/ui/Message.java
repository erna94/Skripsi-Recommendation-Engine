package com.ernchatbot.ui;

public class Message {
    ProductInfo productInfo;

    private String text;
    private boolean belongsToCurrentUser;
    private int replyType;
    public static final int NORMAL_REPLY = 1;
    public static final int PRODUCT_LIST = 2;


    public Message(String text, boolean belongsToCurrentUser) {
        this.text = text;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.replyType = Message.NORMAL_REPLY;
    }

    public String getText() {
        return text;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }

    public int getReplyType() {
        return replyType;
    }

    public void setReplyType(int replyType) {
        this.replyType = replyType;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
