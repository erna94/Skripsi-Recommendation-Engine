package com.ernchatbot.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginService {
    String username_input;
    String password_input;
    static String URL_LOCAL = "http://10.0.2.2:8080";


    public String getLogin(String username_input, String password_input) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL_LOCAL + "/api/login/" + username_input + "/" + password_input);

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
