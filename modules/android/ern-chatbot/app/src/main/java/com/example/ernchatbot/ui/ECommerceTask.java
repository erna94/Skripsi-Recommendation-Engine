package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.ernchatbot.service.ECommerceService;
import com.example.ernchatbot.service.response.Product;
import com.example.ernchatbot.service.response.WitAIResponse;
import com.example.ernchatbot.service.response.WitEntities;
import com.example.ernchatbot.service.response.WitEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ECommerceTask extends AsyncTask<WitAIResponse, Void , List<Product>> {
    MessageAdapter messageAdapter;
    ListView messagesView;

    public final static String CARI_PRODUCT = "cari_product";
    public final static String CARI_CATEGORY = "cari_category";

    public ECommerceTask(MessageAdapter adapter, ListView messagesView) {
        this.messageAdapter = adapter;
        this.messagesView = messagesView;
    }

    @Override
    protected List<Product> doInBackground(WitAIResponse... witAIResponses) {
        String response = null;
        List<Product> products = new ArrayList<Product>();

        // mengevaluasi apakah keinginan user dan apa category yang di cari berdasarkan
        // mapping yang ada di wit.ai
        if(witAIResponses.length > 0) {
            WitAIResponse witAIResponse =  witAIResponses[0];
            WitEntities entities = witAIResponse.getEntities();
            WitEntity[] intents = entities.getIntent();
            WitEntity[] categories = entities.getCategoryProduct();
            WitEntity[] subcategories = entities.getSubCategoryProduct();


            if(intents != null && intents.length > 0) {
                WitEntity intent = intents[0];

                // mendeteksi apakah intent dari user adalah untuk mencari product dengan sesuatu yang pasti
                if(intent.getValue() != null && intent.getValue().equals(CARI_PRODUCT)) {
                    // kondisi untuk menerima permintaan user
                    // confidence di intent > 85%
                    // confidence di kategory > 80%
                    // confidence di sub-category > 80%
                    double confidenceIntent = intent.getConfidence();

                    if(confidenceIntent > .85) {
                        // kalau benar, kita harus mengecheck apa category dan sub-category nya
                        // ada
                        if (categories != null && subcategories != null && categories.length > 0 && subcategories.length > 0
                            && categories[0].getConfidence() > .80
                            && subcategories[0].getConfidence() > .80) {
                            // menggabungkan category dan sub-category untuk mendapatkan
                            // kategoryId untuk barang2 nya
                            // Contoh: Untuk celana wanita, kategory id nya adalah 12
                            // dan untuk pria adalah 22
                            // di dalam Wit kita sudah mengidentifikasi pria dengan 2
                            // dan celana dengan 2 juga. Karena itu, kita bisa menggabung
                            // dua data tersebut menjadi String "12" dan kita convert
                            // menjadi Long untuk mendapatkan 12
                            // memanggil micro service

                            String categoryAsString = categories[0].getValue();
                            String subCategoryAsString = subcategories[0].getValue();
                            Log.println(Log.VERBOSE, "eCommerceTask", "Mencari  " + categoryAsString + " " + subCategoryAsString);
                            Long categoryId = Long.parseLong(categoryAsString + subCategoryAsString);

                            try {
                                ECommerceService service = new ECommerceService();
                                response = service.getProductByCategory(categoryId);
                                ObjectMapper objectMapper = new ObjectMapper();

                                // membuat mapping dari JSON Product list untuk mapping
                                // JSON String ke dalam object
                                products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
                                });
                            } catch (Exception e) {
                                Log.println(Log.VERBOSE, "eCommerceTask", "Tidak menemukan kategory " + categoryId);
                                e.printStackTrace();
                                // abaikan error dan response akan tetap menjadi null
                                // yang kita akan deteksi nanti
                            }
                        }
                    } else {
                        Log.println(Log.VERBOSE, "eCommerceTask", "Confidence " + confidenceIntent + " dengan " + intent.getValue());
                    }
                }
            }
        }

        return products;
    }

    @Override
    protected void onPostExecute(List<Product> products) {
       Message reply = null;

       if(products != null && products.size() > 0) {
           String buatReply = "Ini adalah 20 besar pencarian anda:";

           int productSize = products.size();

           if(productSize > 20) {
               productSize = 20;
           }

           for(int i=0;i<productSize;i++) {
               buatReply += "\n" + (i+1) + ": " + products.get(i).getNamaProduct();
           }

           reply = new Message(buatReply, false);;
        } else {
            reply = new Message("Maaf, saya tak bisa memenuhi request anda", false);

        }

        messageAdapter.add(reply);
        messagesView.setSelection(messagesView.getCount() - 1);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
