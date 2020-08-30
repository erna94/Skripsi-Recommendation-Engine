package com.ernchatbot.service;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class WitAIService {

    // Ini adalah otorisasi yang dibutuhkan oleh Wit AI untuk aplikasi ini
    final static String authToken = "Bearer JZKOF2IQAYQSD6NLGRU363FAYOBKJW7V";

    /***
     *  Memanggil wit AI untuk mendapatkan intent dari suatu message
     * @param message Hasil dari message yang kita mau panggil
     * @return
     * @throws Exception
     */
    public String callWitAI(String message) throws Exception {
        HttpClient client = new DefaultHttpClient();

        // untuk mengirim dengan GET, kita harus mengubah message kita dengan encoding
        // contoh "Saya mau makan" akan menjadi "Saya%20mau%20makan" di mana
        // %20 melambangkan spasi di URL encoding
        String encodedString = URLEncoder.encode(message,  java.nio.charset.StandardCharsets.UTF_8.toString());
        HttpGet request = new HttpGet("https://api.wit.ai/message?v=20160526&q=" + encodedString);

        // untuk memanggil Wit Micro Service, kita membutuhkan authToken untuk mengindetifikasikan diri kita
        request.setHeader("Authorization", authToken);
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

        Log.println(Log.DEBUG, "WitAITask", "Response from WitAI " + allResponse);

        return allResponse;
    }
}
