package com.example.ernchatbot.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ernchatbot.R;
import com.example.ernchatbot.service.WitAIService;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private WitAIService witAIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // text untuk mengkirim chatbot
        editText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        // object untuk memanggil micro service dari Wit AI
        witAIService = new WitAIService();
    }

    /***
     * Mengkirim message ke Chatbot untuk mendapatkan Intent dan setelah
     * itu mengevaluasi intent untuk mendapatkan Micro Service mana yang harus
     * di panggil
     * @param view
     */
    public void sendMessage(View view) {
        final String message = editText.getText().toString();

        if (message.length() > 0) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // contoh message buat yang di kirim
                    Message sentMessage = new Message(message, true);
                    messageAdapter.add(sentMessage);

                    // message buat penerimaan
                    Message reply = new Message("Reply", false);
                    messageAdapter.add(reply);

                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
            editText.getText().clear();
        }
    }

}
