package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.ernchatbot.service.ECommerceService;
import com.example.ernchatbot.service.LoginService;
import com.example.ernchatbot.service.WitAIService;
import com.example.ernchatbot.service.response.LoginResponse;
import com.example.ernchatbot.service.response.Product;
import com.example.ernchatbot.service.response.WitAIResponse;
import com.example.ernchatbot.service.response.WitEntities;
import com.example.ernchatbot.service.response.WitEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LoginTask extends AsyncTask<String, Void, LoginResponse> {

    private LoginResponse loginResponse;

    public LoginTask(LoginResponse loginResponse) {
        // object untuk memanggil micro service dari Wit AI
        this.loginResponse = new LoginResponse();
    }

    @Override
    protected LoginResponse doInBackground(String... message) {
        String username;
        String password;

        LoginResponse loginResponse = new LoginResponse();
        System.out.println(loginResponse.getUserName());

        return loginResponse;
    }

    @Override
    protected void onPostExecute (LoginResponse result){
    }
}
