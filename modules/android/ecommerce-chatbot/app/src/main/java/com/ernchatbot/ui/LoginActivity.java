package com.ernchatbot.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btn_Login;
    EditText username, password;
    TextView yuk;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        btn_Login = (Button) findViewById(R.id.btn_Login);
    }

    public void onLogin(View view){

        final String name = username.getText().toString();
        final String pass = password.getText().toString();
        System.out.println("username: " + username.getText().toString() + "password: " + password.getText().toString());

        LoginTask login = new LoginTask();
        login.execute(name, pass);
    }
}

