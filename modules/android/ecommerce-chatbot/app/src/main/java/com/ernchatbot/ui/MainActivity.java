package com.ernchatbot.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText ernaEditTex;
    ListView ernaListView;
    MessageAdapter ernaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ernaEditTex = findViewById(R.id.ernaEditText);
        this.ernaListView = findViewById(R.id.erna_messages_view);
        this.ernaAdapter = new MessageAdapter(this);
        ernaListView.setAdapter(ernaAdapter);
    }

    public void sendMessage(View view) {
        final String message = ernaEditTex.getText().toString();
        Log.println(Log.VERBOSE, "ernaBot", "Tertekan tombol... HAHA!" + message);

        // kalo input yg dimasukkan di edit text lebih dari nol maka akan dijalankan
        if (message.length() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Message messageBaru = new Message(message,true);
                    ernaAdapter.hapusMessage();
                    ernaAdapter.tambahMessage(messageBaru);
                    WitAITask witAITask = new WitAITask(ernaAdapter, ernaListView);
                    witAITask.execute(message);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }
}
