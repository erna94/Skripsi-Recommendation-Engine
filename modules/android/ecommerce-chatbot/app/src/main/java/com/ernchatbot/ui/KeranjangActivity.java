package com.ernchatbot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Map;
import java.util.Set;

public class KeranjangActivity extends AppCompatActivity {
    ListView ernaListView;
    MessageAdapter ernaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keranjang);
        this.ernaListView = findViewById(R.id.erna_messages_view);
        this.ernaAdapter = new MessageAdapter(this);
        ernaListView.setAdapter(ernaAdapter);

        setupKeranjang();
    }

    private void setupKeranjang() {
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

        Set<Map.Entry<ProductInfo, Integer>> isiCart = MainActivity.cart.entrySet();

        for (Map.Entry<ProductInfo, Integer> isiProduct : isiCart) {
            Message messageIsiCart =
                    new Message("Product List", false);

            messageIsiCart.setReplyType(Message.PRODUCT_LIST);
            messageIsiCart.setProductInfo(isiProduct.getKey());
            ernaAdapter.tambahMessage(messageIsiCart);
        }
    }

    public void checkout(View view) {
        Intent intent = new Intent(this, PembayaranActivity.class);
        startActivity(intent);
    }
}
