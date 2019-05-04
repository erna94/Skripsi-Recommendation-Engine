package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.ernchatbot.service.WitAIService;

import java.net.URL;

public class WitAITask extends AsyncTask<String, Void , String> {
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
    protected String doInBackground(String... message) {
        String response;

        try {
            response = witAIService.callWitAI(message[0]);
        } catch(Throwable t) {
            response = "error from Chatbot";
            t.printStackTrace();
        }

        return response;
    }

    protected void onProgressUpdate() {
    }


    protected void onPostExecute(String result) {
        // message buat penerimaan
        Message reply = new Message(result, false);
        messageAdapter.add(reply);
        messagesView.setSelection(messagesView.getCount() - 1);
    }
}