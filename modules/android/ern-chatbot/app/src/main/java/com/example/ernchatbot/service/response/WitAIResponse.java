package com.example.ernchatbot.service.response;

import com.fasterxml.jackson.annotation.JsonSetter;

/***
 * Object yang merepresentasikan JSON hasil dari Micro Service WitAI seperti contoh di bawah ini
 {
   "_text":"mau baju wanita",
    "entities": {
        "sub_category_produk":[
            {
                "confidence":0.95489074039314,
                "value":"baju",
                "type":"value"
            }
        ],
        "category_produk":[
            {
                "suggested":true,
                "confidence":0.9751864366976,
                "value":"wanita",
                "type":"value"
            }
        ],
        "intent":[
            {
                "confidence":0.9916541635003,
                "value":"cari_product
            "}
        ]
    },
    "msg_id":"12wyHL1rYsNsuBoB4"}
 */
public class WitAIResponse {
    String text;
    String messageId;
    WitEntities entities;

    public String getText() {
        return text;
    }

    @JsonSetter("_text")
    public void setText(String text) {
        this.text = text;
    }

    public String getMessageId() {
        return messageId;
    }

    // Seperti query, ini menandakan kalau kita akan
    // menghubungkan messageId dengan JSON msg_id
    @JsonSetter("msg_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public WitEntities getEntities() {
        return entities;
    }

    public void setEntities(WitEntities entities) {
        this.entities = entities;
    }
}
