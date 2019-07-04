package com.ernchatbot.ui;

import android.os.AsyncTask;
import android.widget.ListView;

import com.ernchatbot.service.WitAIService;
import com.ernchatbot.service.response.WitAIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WitAITask extends AsyncTask<String, Void, WitAIResponse> {
    private WitAIService witAIService;
    MessageAdapter messageAdapter;
    ListView searchView;

    public WitAITask(MessageAdapter adapter, ListView searchView) {
        // object untuk memanggil micro service dari Wit AI
        this.witAIService = new WitAIService();
        this.messageAdapter = adapter;
        this.searchView = searchView;
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
        // ini akan membuat pemanggilan micro-service berantai yang akan mengarah kepada
        // panggilan e-commerce berikutnya
        ECommerceTask eCommerceTask = new ECommerceTask(messageAdapter, searchView);
        eCommerceTask.execute(result);
    }
}