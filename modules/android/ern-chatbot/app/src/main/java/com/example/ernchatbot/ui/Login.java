package com.example.ernchatbot.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ernchatbot.R;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void Login(View view) {
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}
