package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.ernchatbot.service.response.LoginResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginTask extends AsyncTask<String, Void, LoginResponse> {

    private LoginResponse loginResponse;

    public LoginTask(LoginResponse loginResponse) {
        // object untuk memanggil micro service dari Wit AI
        this.loginResponse = new LoginResponse();
    }

    @Override
    protected LoginResponse doInBackground(String... message) {
        String username = message[0];
        String password = message[1];

        LoginResponse loginResponse = new LoginResponse();
        System.out.println(loginResponse.getUserName());

        try {
            username = loginResponse.getUserName();
            password = loginResponse.getPassword();

            // object ini adalah untuk mengubah dari suatu String berbentuk JSON
            // ke dalam object tertentu. Dalam hal ini kita mau mengubah bentuk
            // String ke dalam object WitAIResponse
            ObjectMapper objectMapper = new ObjectMapper();
            loginResponse = objectMapper.readValue(username + password, LoginResponse.class);

        } catch(Throwable t) {
            t.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    protected void onPostExecute (LoginResponse result){
    }
}
