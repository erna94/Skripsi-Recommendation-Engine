package com.ernchatbot.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecognitionListener {

    EditText ernaEditTex;
    ListView ernaListView;
    MessageAdapter ernaAdapter;

    // memakai LinkedHashMap supaya cartnya berurutan
    static Map<ProductInfo, Integer> cart = new LinkedHashMap<ProductInfo, Integer>();
    String speechString = "";
    private String LOG_TAG = "SpeechActivity";
    //deklarasi penggunaan fitur speech recognizer
    private SpeechRecognizer speech = null;
    TextView editText;
    Intent mSpeechRecognizerIntent;
    Intent recognizerIntent;
    private AudioManager manager;
    public static final Integer RecordAudioRequestCode = 1;
    ImageView btnPtt;
    ImageButton sendMessage;

    //methode untuk mengulang sesi penerimaan suara yang akan diterjemahkan kedalam teks
    private void resetSpeechRecognizer() {

        if(speech != null)
            speech.destroy();
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        if(SpeechRecognizer.isRecognitionAvailable(this))
            speech.setRecognitionListener(this);
        else
            finish();
    }

    //inisialisasi speech recognizer
    private void setRecogniserIntent() {

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //melakukan pemilihan bahasa yang akan diolah pada saat penerimaan suara dan diterjemahkan menjadi tulisan
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "in");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        this.ernaEditTex = findViewById(R.id.ernaEditText);
        this.ernaListView = findViewById(R.id.erna_messages_view);
        this.ernaAdapter = new MessageAdapter(this);
        sendMessage = findViewById(R.id.sendMessage);
        btnPtt = findViewById(R.id.btnPtt);
        ernaListView.setAdapter(ernaAdapter);

        speech = SpeechRecognizer.createSpeechRecognizer(this);

        setRecogniserIntent();
        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        findViewById(R.id.btnPtt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSpeechRecognizer();
                speech.startListening(recognizerIntent);
                ernaEditTex.setText(""
                );
            }
        });

        final TextWatcher txwatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    btnPtt.setVisibility(View.GONE);
                    sendMessage.setVisibility(View.VISIBLE);
                }
                else  if(s.length()==0){
                    btnPtt.setVisibility(View.VISIBLE);
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
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
                    }
                }
            }

            //methode yang menghandle hasil dari pengecekan akses
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }
            }

    @Override
    public void onResume() {
        Log.i(LOG_TAG, "resume");
        super.onResume();
    }
    //melakukan stop speechrecognizer pada saat keluar dari halaman
    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "pause");
        super.onPause();
        speech.stopListening();
    }
    //melakukan stop speechrecognizer pada saat aplikasi berhenti
    @Override
    protected void onStop() {
        Log.i(LOG_TAG, "stop");
        super.onStop();
        if (speech != null) {
            speech.destroy();
        }
    }
    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }
    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
    }
    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }
    //menghentikan speechrecognizer saat tidak terdeteksi lagi suara yang dapat diterjemahkan
    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        speech.stopListening();

    }
    //mengeluarkan hasil dari penerjemahan speechrecognizer yang berupa text
    @Override
    public void onResults(Bundle bundle) {
        Log.i(LOG_TAG, "onResults");
        //getting all the matches
        ArrayList<String> matches = bundle
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechString = speechString + " " + matches.get(0);
        //displaying the first match
        //displaying the first match
        ernaEditTex.setText(speechString );

        //speech.startListening(recognizerIntent);
    }
    //methode untuk menghandle saat terjadi error
    @Override
    public void onError(int i) {
        String errorMessage = getErrorText(i);
        Log.i(LOG_TAG, "FAILED " + errorMessage);
        // rest voice recogniser
        resetSpeechRecognizer();
        speech.startListening(recognizerIntent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message messageBaru = new Message(speechString,true);
                ernaAdapter.hapusMessage();
                ernaAdapter.tambahMessage(messageBaru);
                WitAITask witAITask = new WitAITask(ernaAdapter, ernaListView);
                witAITask.execute(speechString);
            }
        });
    }

    @Override
    public void onRmsChanged(float v) {
    }
    @Override
    public void onPartialResults(Bundle bundle) {
    }
    @Override
    public void onEvent(int i, Bundle bundle) {
    }

    public String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    public void onMic(View view){

    }

    public void sendMessage(View view) {
        final String message = ernaEditTex.getText().toString();
        Log.println(Log.VERBOSE, "ernaBot", "Tertekan tombol... " + message);

        // kalo input yg dimasukkan di edit text lebih dari nol maka akan dijalankan
        if (message.length() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Message messageBaru = new Message(message,true);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

