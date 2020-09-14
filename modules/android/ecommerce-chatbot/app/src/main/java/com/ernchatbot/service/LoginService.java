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
    static String URL_LOCAL = "http://10.0.2.2:8080";

    public String getLogin(String username_input, String password_input) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL_LOCAL + "/api/login/" + username_input + "/" + password_input);
        HttpResponse response = client.execute(request);
        return getAPIResponse(response);
    }

    public String createNewLogin(String email, String username, String password) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL_LOCAL + "/api/login");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
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
        HttpPost httpPost = new HttpPost(URL_LOCAL + "/api/user");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

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
