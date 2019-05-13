package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.ernchatbot.service.response.WitAIResponse;
import com.example.ernchatbot.service.response.WitEntities;
import com.example.ernchatbot.service.response.WitEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class ECommerceTask extends AsyncTask<WitAIResponse, Void , String> {
    MessageAdapter messageAdapter;
    ListView messagesView;

    public final static String CARI_PRODUCT = "cari_product";
    public final static String CARI_CATEGORY = "cari_category";

    public ECommerceTask(MessageAdapter adapter, ListView messagesView) {
        this.messageAdapter = adapter;
        this.messagesView = messagesView;
    }

    @Override
    protected String doInBackground(WitAIResponse... witAIResponses) {
        String response = null;

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
                        if(categories.length > 0 && subcategories.length > 0) {
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
                            Log.println(Log.VERBOSE, "eCommerceTask", "Mencari  " + categoryAsString + " " + subCategoryAsString );

                            Long categoryId = Long.parseLong(categoryAsString + subCategoryAsString );

                            // memanggil micro service
                            try {
                                response = findProduct(categoryId);
                            } catch(Exception e) {
                                Log.println(Log.VERBOSE, "eCommerceTask", "Tidak menemukan kategory " + categoryId);
                                e.printStackTrace();
                                // abaikan error dan response akan tetap menjadi null
                                // yang kita akan deteksi nanti
                            }
                        }
                    }
                }
            }
        }

        return response;
    }

    private String findProduct(Long categoryId) throws Exception {
        HttpClient client = new DefaultHttpClient();

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
    protected void onPostExecute(String result) {
       Message reply = null;

       if(result != null) {
            reply = new Message(result, false);;
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
