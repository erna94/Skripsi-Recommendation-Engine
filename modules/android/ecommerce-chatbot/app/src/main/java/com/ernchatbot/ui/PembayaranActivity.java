package com.ernchatbot.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class PembayaranActivity extends AppCompatActivity {

    ListView ernaListView;
    MessageAdapter ernaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pembayaran);
    }
}
