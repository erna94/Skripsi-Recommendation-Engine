package com.example.ernchatbot.service;

import android.util.Log;

import com.example.ernchatbot.ui.LoginActivity;
import com.example.ernchatbot.ui.Message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginService {
    String username_id;
    String password_id;


    public String getLogin(String username_id, String password_id) throws Exception {
        //bikin object baru
        LoginService login = new LoginService();
        //panggil object dan print
        System.out.println(login.getLogin("erna", "abc123"));

        return username_id;
    }
}

