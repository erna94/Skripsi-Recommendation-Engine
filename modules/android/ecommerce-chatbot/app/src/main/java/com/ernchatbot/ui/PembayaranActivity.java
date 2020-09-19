package com.ernchatbot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class PembayaranActivity extends AppCompatActivity {

    ListView ernaListView;
    MessageAdapter ernaAdapter;
    TextView totalPembayaran;
    EditText emailPemesan;
    Button btn_Pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pembayaran);

        this.ernaListView = findViewById(R.id.erna_messages_view);
        this.ernaAdapter = new MessageAdapter(this);
        this.totalPembayaran = findViewById(R.id.txt_total_pembayaran);
        this.emailPemesan = findViewById(R.id.email_pemesan);

        btn_Pembayaran = (Button) findViewById(R.id.btn_pembayaran);
        ernaListView.setAdapter(ernaAdapter);
        setupRingkasanPembayaran();
    }


    private void setupRingkasanPembayaran() {
        Set<Map.Entry<ProductInfo, Integer>> isiCart = MainActivity.cart.entrySet();
        Double totalHarga = 0d;

        for (Map.Entry<ProductInfo, Integer> isiProduct : isiCart) {
            ProductInfo productInfo = isiProduct.getKey();
            Message messageIsiCart =
                    new Message("Product List", false);

            messageIsiCart.setReplyType(Message.PRODUCT_LIST);
            messageIsiCart.setProductInfo(productInfo);
            ernaAdapter.tambahMessage(messageIsiCart);

            totalHarga = totalHarga + productInfo.getItemPrice();
        }

        this.totalPembayaran.setText(totalHarga + " IDR");
    }

    public void onBayar(View view) {
        Intent intent = new Intent(PembayaranActivity.this, MainActivity.class);
        startActivity(intent);


    }
}
