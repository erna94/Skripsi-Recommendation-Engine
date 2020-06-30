package com.example.ernchatbot.service;

import android.util.Log;
import com.example.ernchatbot.ui.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginService {
    String username1;
    String password1;


    public String getLogin(String username1, String password1) throws Exception {
        //bikin object baru
        LoginService login = new LoginService();
        //panggil object dan print
        System.out.println(login.getLogin("erna", "abc123"));

        return username1;
    }


}

