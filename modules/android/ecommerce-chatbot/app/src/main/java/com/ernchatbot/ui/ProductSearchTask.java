package com.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.ernchatbot.service.ProductSearchService;
import com.ernchatbot.service.response.Product;
import com.ernchatbot.service.response.WitAIResponse;
import com.ernchatbot.service.response.WitEntities;
import com.ernchatbot.service.response.WitEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchTask extends AsyncTask<WitAIResponse, Void, List<Product>> {
    MessageAdapter messageAdapter;
    ListView searchView;

    public final static String CARI_PRODUCT = "cari_product";
    public final static String CARI_CATEGORY = "cari_category";

    public ProductSearchTask(MessageAdapter adapter, ListView messagesView) {
        this.messageAdapter = adapter;
        this.searchView = messagesView;
    }

    @Override
    protected List<Product> doInBackground(WitAIResponse... witAIResponses) {
        String response = null;
        List<Product> products = new ArrayList<Product>();
        Log.println(Log.VERBOSE, "android-app", "Mendapatkan response dengan size "+ witAIResponses.length);

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
                    // confidence di kategory > 70%
                    // confidence di sub-category > 70%
                    double confidenceIntent = intent.getConfidence();
                    Log.println(Log.VERBOSE, "android-app", "Mendapatkan intent dengan " +
                            "confidence "+ confidenceIntent);

                    if(confidenceIntent > .85) {
                        // kalau benar, kita harus mengecheck apa category dan sub-category nya
                        // ada
                        if (categories != null && subcategories != null && categories.length > 0
                                && subcategories.length > 0
                            && categories[0].getConfidence() > .70
                            && subcategories[0].getConfidence() > .70) {
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
                            Log.println(Log.VERBOSE, "eCommerceTask",
                                    "Mencari  " + categoryAsString + " " + subCategoryAsString);
                            Long categoryId = Long.parseLong(categoryAsString + subCategoryAsString);

                            try {
                                ProductSearchService service = new ProductSearchService();
                                response = service.getProductByCategory(categoryId);
                                ObjectMapper objectMapper = new ObjectMapper();

                                // membuat mapping dari JSON Product list untuk mapping
                                // JSON String ke dalam object
                                products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
                                });
                            } catch (Exception e) {
                                Log.println(Log.VERBOSE, "eCommerceTask",
                                        "Tidak menemukan kategory " + categoryId);
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
        Log.println(Log.VERBOSE, "android-app", "Mendapatkan product sejumlah "+ products.size());
        if(products.isEmpty()) {
            Message balasanSiBot =
                    new Message("Maaf :( Saya tidak bisa memenuhi " +
                            "keinginan anda", false);

            balasanSiBot.setReplyType(Message.NORMAL_REPLY);
            messageAdapter.tambahMessage(balasanSiBot);
        } else {
            messageAdapter.setProducts(products);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
