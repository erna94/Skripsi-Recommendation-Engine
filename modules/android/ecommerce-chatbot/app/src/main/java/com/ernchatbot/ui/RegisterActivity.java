package com.ernchatbot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    Button btn_Register;
    EditText username, email, password,  umur, lokasi, pekerjaan;
    TextView yuk;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username_edit);
        email = (EditText) findViewById(R.id.email_edit);
        password = (EditText) findViewById(R.id.password_edit);
        umur = (EditText) findViewById(R.id.umur_edit);
        lokasi = (EditText) findViewById(R.id.lokasi_edit);
        pekerjaan = (EditText) findViewById(R.id.pekerjaan_edit);
        btn_Register = (Button) findViewById(R.id.btn_Register);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MEMULAI REGISTER ACTIVITY");
    }

    public void onRegister(View view){

        //intent pindah halaman
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    public void onLoginText(View view){

        //intent pindah halaman
        Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent2);

    }

}
