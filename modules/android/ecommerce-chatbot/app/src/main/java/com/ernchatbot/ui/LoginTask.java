package com.ernchatbot.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.ernchatbot.service.response.LoginResponse;
import com.ernchatbot.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginTask extends AsyncTask<String, Void, LoginResponse> {

    private LoginResponse loginResponse;
    public static Long loggedInUser;

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
            String r = loginService.getLogin(username_input, password_input);
            System.out.println(r);

            // object ini adalah untuk mengubah dari suatu String berbentuk JSON
            // ke dalam object tertentu. Dalam hal ini kita mau mengubah bentuk
            // String ke dalam object loginResponse
            ObjectMapper objectMapper = new ObjectMapper();
            loginResponse = objectMapper.readValue(r, LoginResponse.class);

            String username_output = loginResponse.getUserName();
            String password_output = loginResponse.getPassword();
            Log.println(Log.DEBUG,  this.getClass().toString(), "\n\n ######### ini dari asyntask username =" + username_output + " dengan password =" + password_output);


        } catch(Throwable r) {
            r.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    protected void onPostExecute (LoginResponse result){
        // set ke static kalo ada user ID nya buat di pake
        LoginTask.loggedInUser = result.getIdUser();
    }
}
