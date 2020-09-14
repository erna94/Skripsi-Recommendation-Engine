package com.ernchatbot.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ernchatbot.service.LoginService;
import com.ernchatbot.service.response.LoginResponse;
import com.ernchatbot.service.response.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RegisterTask extends AsyncTask<String, Void, LoginResponse> {

    private LoginResponse loginResponse;
    public static Long loggedInUser;
    Activity context;

    public RegisterTask(Activity context) {
        this.context = context;
    }

    @Override
    protected LoginResponse doInBackground(String... parameters) {

        LoginResponse loginResponse = new LoginResponse();
        try {
            String usernameBaru = parameters[0];
            String emailBaru = parameters[1];
            String passwordBaru = parameters[2];
            String umurBaru = parameters[3];
            String lokasiBaru = parameters[4];
            String pekerjaanBaru  = parameters[5];

            //membuat Login baru dulu
            LoginService loginService = new LoginService();
            String response = loginService.createNewLogin(emailBaru, usernameBaru, passwordBaru);
            Log.println(Log.DEBUG,  this.getClass().toString(), "\n\n #########createNewLogin =" + response);

            ObjectMapper objectMapper = new ObjectMapper();
            loginResponse = objectMapper.readValue(response, new TypeReference<LoginResponse>() {});

            // skrg kita membuat User baru
            Long idBaru = loginResponse.getIdUser(); // ini kita mengambil id baru Login yang baru dibuat
            String responseDariCreateUser = loginService.createNewUser(idBaru, umurBaru, lokasiBaru, pekerjaanBaru);
            Log.println(Log.DEBUG,  this.getClass().toString(), "\n\n #########responseDariCreateUser =" + responseDariCreateUser);

        } catch(Throwable r) {
            r.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    protected void onPostExecute (LoginResponse result){
        // set ke static kalo ada user ID nya buat di pake
        LoginTask.loggedInUser = result.getIdUser();


        //intent pindah halaman
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
