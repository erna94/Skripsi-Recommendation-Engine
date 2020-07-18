package com.example.ernchatbot.ui;

import android.os.AsyncTask;
import com.example.ernchatbot.service.LoginService;
import com.example.ernchatbot.service.response.LoginResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class LoginTask extends AsyncTask<String, Void, LoginResponse> {

    private LoginResponse loginResponse;

    public LoginTask() {

    }

    @Override
    protected LoginResponse doInBackground(String... message) {
        String username_input = message[0];
        String password_input = message[1];

        LoginResponse loginResponse = new LoginResponse();
        System.out.println(loginResponse.getUserName());

        try {

            LoginService loginService= new LoginService();
            String r = loginService.getLogin("erna", "abc123");
            System.out.println(r);

            // object ini adalah untuk mengubah dari suatu String berbentuk JSON
            // ke dalam object tertentu. Dalam hal ini kita mau mengubah bentuk
            // String ke dalam object loginResponse
            ObjectMapper objectMapper = new ObjectMapper();
            loginResponse = objectMapper.readValue(r, LoginResponse.class);

            String username_output = loginResponse.getUserName();
            String password_output = loginResponse.getPassword();
            System.out.println("\n\n ini dari asyntask username =" + username_output + "password =" + password_output);

        } catch(Throwable r) {
            r.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    protected void onPostExecute (LoginResponse result){
    }
}
