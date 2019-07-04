package com.ernchatbot.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText ernaEditTex;
    ListView ernaListView;
    MessageAdapter ernaAdapter;

    // memakai LinkedHashMap supaya cartnya berurutan
    static Map<ProductInfo, Integer> cart = new LinkedHashMap<ProductInfo, Integer>();

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
    // fungsi buat menampilkan keranjang icon di menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    // fungsi untuk menghandle sewaktu cart icon di click user
    public boolean onOptionsItemSelected(MenuItem item) {
        this.ernaEditTex.setText("");
        hideKeyboardFrom(this,  this.getCurrentFocus());

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cart:
                Log.println(Log.VERBOSE, "ernaBot", "Masuk ke cart....");
                ernaAdapter.hapusMessage();
                String defaultMessage = "Keranjang anda kosong, silakan berbelanja dulu";

                if(!MainActivity.cart.isEmpty()) {
                    defaultMessage = "Berikut ini adalah isi keranjang anda";
                }

                Message balasanSiBot =
                        new Message(defaultMessage, false);

                balasanSiBot.setReplyType(Message.NORMAL_REPLY);
                ernaAdapter.tambahMessage(balasanSiBot);

                Set<Map.Entry<ProductInfo, Integer>> isiCart = cart.entrySet();

                for (Map.Entry<ProductInfo, Integer> isiProduct : isiCart) {
                    Message messageIsiCart =
                            new Message("Product List", false);

                    messageIsiCart.setReplyType(Message.PRODUCT_LIST);
                    messageIsiCart.setProductInfo(isiProduct.getKey());
                    ernaAdapter.tambahMessage(messageIsiCart);
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
