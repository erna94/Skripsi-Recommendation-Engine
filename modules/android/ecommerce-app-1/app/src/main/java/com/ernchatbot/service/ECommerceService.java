package com.ernchatbot.service;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
