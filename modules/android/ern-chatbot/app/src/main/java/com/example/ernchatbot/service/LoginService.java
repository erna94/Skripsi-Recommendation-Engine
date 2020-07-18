package com.example.ernchatbot.service;

import android.util.Log;

import com.example.ernchatbot.service.response.LoginResponse;
import com.example.ernchatbot.ui.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginService {
    String username_input;
    String password_input;


    public String getLogin(String username_input, String password_input) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://127.0.0.1:8080/api/login/" + username_input + password_input);

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







