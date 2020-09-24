package com.ernchatbot.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText ernaEditTex;
    ListView ernaListView;
    MessageAdapter ernaAdapter;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.about1, R.drawable.about2, R.drawable.about3, R.drawable.about4};
    // memakai LinkedHashMap supaya cartnya berurutan
    static Map<ProductInfo, Integer> cart = new LinkedHashMap<ProductInfo, Integer>();
    String speechString = "";
    //deklarasi penggunaan fitur speech recognizer
    TextView editText;

    public static final Integer RecordAudioRequestCode = 1;
    ImageView micButton;
    ImageButton sendMessage;
    MicListener micListener;
    boolean isListening = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.micListener = new MicListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
        this.ernaEditTex = findViewById(R.id.ernaEditText);
        ernaEditTex.setHint("Ketik atau tekan tombol mic..");

        this.ernaListView = findViewById(R.id.erna_messages_view);
        this.micButton = findViewById(R.id.btnPtt);
        this.ernaAdapter = new MessageAdapter(this);


        sendMessage = findViewById(R.id.sendMessage);
        ernaListView.setAdapter(ernaAdapter);

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isListening) {
                    ernaEditTex.setHint("Listening... Klik tombol mic buat stop");
                    micListener.startListening();
                    ernaEditTex.setText("");
                    isListening = true;
                } else {
                    ernaEditTex.setHint("Ketik atau tekan tombol mic..");
                    isListening = false;
                    micListener.stopListening();
                }
            }
        });

        final TextWatcher txwatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    micButton.setVisibility(View.GONE);
                    sendMessage.setVisibility(View.VISIBLE);
                } else if (s.length() == 0) {
                    micButton.setVisibility(View.VISIBLE);
                    sendMessage.setVisibility(View.GONE);
                }
            }

            public void afterTextChanged(Editable s) {
            }
        };
        ernaEditTex.addTextChangedListener(txwatcher);
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
            }
        }
    }

    //methode yang menghandle hasil dari pengecekan akses
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        Log.i(this.getClass().toString(), "resume");
        super.onResume();
    }

    //melakukan stop speechrecognizer pada saat keluar dari halaman
    @Override
    protected void onPause() {
        Log.i(this.getClass().toString(), "pause");
        super.onPause();
    }

    //melakukan stop speechrecognizer pada saat aplikasi berhenti
    @Override
    protected void onStop() {
        Log.i(this.getClass().toString(), "stop");
        super.onStop();
    }

    //mengeluarkan hasil dari penerjemahan speechrecognizer yang berupa text
    public void finishSpeech(Bundle bundle) {
        Log.i(this.getClass().toString(), "onResults");
        //getting all the matches
        ArrayList<String> matches = bundle
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechString = matches.get(0);

        //displaying the first match
        ernaEditTex.setText(speechString);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message messageBaru = new Message(speechString, true);
                ernaAdapter.hapusMessage();
                ernaAdapter.tambahMessage(messageBaru);
                WitAITask witAITask = new WitAITask(ernaAdapter, ernaListView);
                witAITask.execute(speechString);
                ernaEditTex.setHint("Write a message");
            }
        });
    }


    public void sendMessage(View view) {
        final String message = ernaEditTex.getText().toString();
        Log.println(Log.VERBOSE, "ernaBot", "Tertekan tombol... " + message);
        //carouselView.setVisibility(View.GONE);
        //ernaListView.setVisibility(View.VISIBLE);

        // kalo input yg dimasukkan di edit text lebih dari nol maka akan dijalankan
        if (message.length() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Message messageBaru = new Message(message, true);
                    ernaAdapter.hapusMessage();
                    ernaAdapter.tambahMessage(messageBaru);
                    WitAITask witAITask = new WitAITask(ernaAdapter, ernaListView);
                    witAITask.execute(message);
                }
            });
        }
    }

    @Override
    // fungsi buat menampilkan keranjang icon di menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    // fungsi untuk menghandle click sewaktu cart icon di click user
    public boolean onOptionsItemSelected(MenuItem item) {
        this.ernaEditTex.setText("");
        //hideKeyboardFrom(this,  this.getCurrentFocus());

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(this, KeranjangActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_logout:
                //MASUKIN LOGOUT DISINI
                // Hilangkan barang di cart
                MainActivity.cart.clear();
                Intent logout = new Intent(this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            case R.id.action_about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}