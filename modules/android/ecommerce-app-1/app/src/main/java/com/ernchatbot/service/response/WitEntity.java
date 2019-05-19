package com.ernchatbot.service.response;

/***
 * Merepresentasikan bagian dari JSON dari Wit.ai yang di bawah ini
    {
        "confidence":0.95489074039314,
        "value":"baju",
        "type":"value"
    }
 */
public class WitEntity {
    private double confidence;
    private String value;
    private String text;
    private String type;
    private boolean suggested;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSuggested() {
        return suggested;
    }

    public void setSuggested(boolean suggested) {
        this.suggested = suggested;
    }
}
