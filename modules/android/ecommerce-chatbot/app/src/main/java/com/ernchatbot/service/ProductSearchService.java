package com.ernchatbot.service;

import android.net.Uri;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductSearchService {
    static String URL_AWS = "http://54.169.172.250:8080";
    static String URL_LOCAL = "http://10.0.2.2:8080/";

    public String getProductByCategory(Long categoryId) throws Exception {

        String url = URL_LOCAL + "/api/produk/list/" + categoryId;
        String response = callHttpGet(url);
        return response;
    }

    public String getRecommendation(Long userId) throws Exception {
        String url = URL_LOCAL + "/api/produk/rekomendasi/" + userId;
        String response = callHttpGet(url);
        return response;
    }

    private String callHttpGet(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();

        // di Android Emulator di Android Studio, localhost di tulis sebagai 10.0.2.2
        HttpGet request = new HttpGet(url);
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
