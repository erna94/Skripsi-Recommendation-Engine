package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.ernchatbot.service.WitAIService;
import com.example.ernchatbot.service.response.WitAIResponse;
import com.example.ernchatbot.service.response.WitEntities;
import com.example.ernchatbot.service.response.WitEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class WitAITask extends AsyncTask<String, Void , WitAIResponse> {
    private WitAIService witAIService;
    MessageAdapter messageAdapter;
    ListView messagesView;

    public WitAITask(MessageAdapter adapter, ListView messagesView) {
        // object untuk memanggil micro service dari Wit AI
        this.witAIService = new WitAIService();
        this.messageAdapter = adapter;
        this.messagesView = messagesView;
    }

    @Override
    protected WitAIResponse doInBackground(String... message) {
        String response;
        WitAIResponse witResponse = new WitAIResponse();

        try {
            response = witAIService.callWitAI(message[0]);

            // object ini adalah untuk mengubah dari suatu String berbentuk JSON
            // ke dalam object tertentu. Dalam hal ini kita mau mengubah bentuk
            // String ke dalam object WitAIResponse
            ObjectMapper objectMapper = new ObjectMapper();
            witResponse = objectMapper.readValue(response, WitAIResponse.class);
        } catch(Throwable t) {
            t.printStackTrace();
        }

        return witResponse;
    }

    protected void onProgressUpdate() {
    }


    protected void onPostExecute(WitAIResponse result) {
        // message buat penerimaan
        Message reply;

        // dapatkan intent dari object WitAIResponse
        WitEntities entities = result.getEntities();
        WitEntity[] intent = entities.getIntent();

        if(result != null &&  intent != null && intent.length > 0) {
            reply = new Message("Sepertinya intent kamu adalah " + intent[0].getValue()
                    + " dengan kemungkinan " + intent[0].getConfidence()*100 + "%", false);
        } else {
            reply = new Message("Maaf, saya tak bisa memenuhi request anda", false);

        }

        messageAdapter.add(reply);
        messagesView.setSelection(messagesView.getCount() - 1);
    }
}