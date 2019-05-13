package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

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

                // mendeteksi apakah intent dari user adalah untuk mencari product
                if(intent.getValue() != null && intent.getValue().equals(CARI_PRODUCT)) {
                    // kalau benar, mari kita check apakah secara persentase keyakinan
                    // di atas 90%
                    double confidence = intent.getConfidence();
                    if(confidence > .90) {
                        // kalau benar, kita harus mengecheck apa category dan sub-category nya
                        // ada
                        if (categories != null && categories.length > 0 && subcategories.length > 0) {
                            // menggabungkan category dan sub-category untuk mendapatkan
                            // kategoryId untuk barang2 nya
                            // Contoh: Untuk celana wanita, kategory id nya adalah 12
                            // dan untuk pria adalah 22
                            // di dalam Wit kita sudah mengidentifikasi pria dengan 2
                            // dan celana dengan 2 juga. Karena itu, kita bisa menggabung
                            // dua data tersebut menjadi String "12" dan kita convert
                            // menjadi Long untuk mendapatkan 12

                            String categoryAsString = categories[0].getValue();
                            String subCategoryAsString = subcategories[0].getValue();
                            Log.println(Log.VERBOSE, "eCommerceTask", "Mencari  " + categoryAsString + " " + subCategoryAsString);

                            Long categoryId = Long.parseLong(categoryAsString + subCategoryAsString);

                            // memanggil micro service
                            try {
                                response = findProduct(categoryId);
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
                        Log.println(Log.VERBOSE, "eCommerceTask", "Confidence " + confidence + " dengan " + intent.getValue());
                    }
                }
            }
        }

        return products;
    }

    private String findProduct(Long categoryId) throws Exception {
        HttpClient client = new DefaultHttpClient();

        // di Android Emulator di Android Studio, localhost di tulis sebagai 10.0.2.2
        Log.println(Log.VERBOSE, "eCommerceTask", "Lokasi micro service " + "http://10.0.2.2:8080/api/produk/list/" + categoryId);
        HttpGet request = new HttpGet("http://10.0.2.2:8080/api/produk/list/" + categoryId);


        HttpResponse response = client.execute(request);

        // Get the response
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        String allResponse = "";
        String line = "";
        while ((line = rd.readLine()) != null) {
            // selama masih ada baris dari hasil pemanggilan micro service, kita akan terus menambah baris tersebut
            // ke hasil kita
            allResponse = allResponse + line;
        }

        return allResponse;
    }

    @Override
    protected void onPostExecute(List<Product> products) {
       Message reply = null;

       if(products != null && products.size() > 0) {
           String buatReply = "Ini adalah 5 besar pencarian anda:";

           int productSize = products.size();

           if(productSize > 5) {
               productSize = 5;
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
