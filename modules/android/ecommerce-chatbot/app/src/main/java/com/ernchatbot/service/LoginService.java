package com.ernchatbot.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginService {
    String username_input;
    String password_input;

    public String getLogin(String username_input, String password_input) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URLConfig.URL_DEVICE_WITH_USB + "/api/login/" + username_input + "/" + password_input);
        HttpResponse response = client.execute(request);
        return getAPIResponse(response);
    }

    public String createNewLogin(String email, String username, String password) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URLConfig.URL_DEVICE_WITH_USB + "/api/login");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        /****
         * Ini menciptakan input yang sama seperti di REST Client yaitu berbentuk JSON berikut
         * {
         * "email": "userbaru1@gmail.com",
         * "userName": "user_baru1",
         * "password": "abc123"
         * }
         */
        String json = "{\n" +
                "\"email\": \"" + email + "\",\n" +
                "\"userName\": \""+ username +  "\",\n" +
                "\"password\": \""+ password + "\"\n" +
                "}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = client.execute(httpPost);
        String response = getAPIResponse(httpResponse);
        return response;
    }

    public String createNewUser(Long idUser, String umur, String lokasi, String pekerjaan) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URLConfig.URL_DEVICE_WITH_USB + "/api/user");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        /***
         * Menciptakan JSON yang sama seperti di REST Client, idUser di sini adalah idUser yang
         * baru diciptakan
         * {
         *   "idUser": 51,
         *   "lokasi": "dki jakarta",
         *   "pekerjaan": "pelajar",
         *   "umur": 15
         * }
         */
        String json = "{\n" +
                "\"idUser\": " + idUser + ",\n" +
                "\"lokasi\": \"" + lokasi + "\",\n" +
                "\"pekerjaan\": \""+ pekerjaan +  "\",\n" +
                "\"umur\": "+ umur + "\n" +
                "}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = client.execute(httpPost);
        String response = getAPIResponse(httpResponse);
        return response;
    }


    private String getAPIResponse(HttpResponse response) throws IOException {
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
