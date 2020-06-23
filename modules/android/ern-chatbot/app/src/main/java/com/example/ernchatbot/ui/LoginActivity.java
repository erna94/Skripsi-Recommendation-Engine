package com.example.ernchatbot.ui;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ernchatbot.R;

public class LoginActivity extends AppCompatActivity {

    Button btn_Login;
    EditText username_id, password_id;
    TextView yuk;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_id = (EditText) findViewById(R.id.username_id);
        password_id = (EditText) findViewById(R.id.password_id);
        btn_Login = (Button) findViewById(R.id.btn_Login);


        }
    public void login(View view){
        final String username = username_id.getText().toString();
        final String password = password_id.getText().toString();
        System.out.println(username_id.getText().toString() + "password" + password_id.getText().toString());

    }
}
