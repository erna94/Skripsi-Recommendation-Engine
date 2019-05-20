package com.ernchatbot.service;

import android.net.Uri;
import android.util.Log;

import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.ProductCategoryModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ECommerceService {
    public String getProductByCategory(Long categoryId) throws Exception {
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

    // Menggunakan daftar dari database dengan Category ID yang benar
    public void initCategory() {
        // ERNA: Perubahan dari fake category untuk menggunakan category asli dari micro service
        ArrayList<ProductCategoryModel> listOfCategory = new ArrayList<ProductCategoryModel>();


        // gambar alternatif
        // http://blog.seasonsway.com/wp-content/uploads/2016/10/mzndyi3pqc2.png

        Log.println(Log.VERBOSE, "android-app", "banner URL " + Uri.parse("R.drawable.banner_11").toString());

        listOfCategory
                .add(new ProductCategoryModel(
                        "Atasan Wanita",
                        "Kemeja, Kaos",
                        "0%",
                        "file:///android_asset/images/banner_11.jpg",
                        11l));

        listOfCategory
                .add(new ProductCategoryModel(
                        "Celana Wanita",
                        "Celana Panjang, Celana Pendek, Celana Jeans",
                        "0%",
                        "file:///android_asset/images/banner_12.jpg",
                        12l));

        CenterRepository.getCenterRepository().setListOfCategory(listOfCategory);
    }
}
