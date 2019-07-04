package com.ernchatbot.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

                    // ERN_STEP 1: mengubah si text jadi Object Message
                    Message sentMessage = new Message(message, true);

                    // ERN_STEP 2: object Message di kirim ke Adapter
                    ernaAdapter.tambahMessage(sentMessage);

                    Message balasanSiBot =
                            new Message("AKU TIRU:" + message, false);

                    balasanSiBot.setReplyType(Message.PRODUCT_LIST);

                    ProductInfo productInfoPalsu = new ProductInfo();
                    productInfoPalsu.setImageLink("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/18/2420174/2420174_3ebb3977-fa7f-4769-b028-8190ec167085_700_700.jpg");
                    productInfoPalsu.setItemName("SEPATU FENDI FLAT MIRROR AUTHENTIC");
                    productInfoPalsu.setItemPrice(10000d);
                    productInfoPalsu.setItemDescription("Include box,dustbag and paperbag");


                    balasanSiBot.setProductInfo(productInfoPalsu);

                    Message balasanSiBot2 =
                            new Message("AKU TIRU:" + message, false);
                    balasanSiBot2.setReplyType(Message.PRODUCT_LIST);

                    ProductInfo productInfoPalsu2 = new ProductInfo();
                    productInfoPalsu2.setImageLink("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/3/4/21076064/21076064_69faeffc-5af0-427b-9870-b8ba49982a1f_1065_1067.jpg");
                    productInfoPalsu2.setItemName("HEELS FASHION WANITA HITAM'");
                    productInfoPalsu2.setItemPrice(10000d);
                    productInfoPalsu2.setItemDescription("Ukuran Sepatu : 34-40");
                    balasanSiBot2.setProductInfo(productInfoPalsu2);

                    ernaAdapter.tambahMessage(balasanSiBot);
                    ernaAdapter.tambahMessage(balasanSiBot2);

                }
            });
        }
    }
}
