package com.ernchatbot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

class MicListener  implements RecognitionListener {
    private String LOG_TAG = "SpeechActivity";

    private SpeechRecognizer speech = null;
    private MainActivity context;
    Intent recognizerIntent;

    MicListener(MainActivity context) {
        this.context = context;
        initSpeechRecogizer();
        initRecognizerIntent();
    }

    //methode untuk mengulang sesi penerimaan suara yang akan diterjemahkan kedalam teks
    private void initSpeechRecogizer() {
        speech = SpeechRecognizer.createSpeechRecognizer(context);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(context));
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speech.setRecognitionListener(this);
        } else {
            context.finish();
        }
    }

    //inisialisasi speech recognizer
    private void initRecognizerIntent() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //melakukan pemilihan bahasa yang akan diolah pada saat penerimaan suara dan diterjemahkan menjadi tulisan
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                "id-ID");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }

    public void stopListening() {
        speech.stopListening();
    }

    public void startListening() {
        speech.startListening(recognizerIntent);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {}

    @Override
    public void onBeginningOfSpeech() {}

    @Override
    public void onRmsChanged(float rmsdB) {}

    //menghentikan speechrecognizer saat tidak terdeteksi lagi suara yang dapat diterjemahkan
    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
    }
    //mengeluarkan hasil dari penerjemahan speechrecognizer yang berupa text
    @Override
    public void onResults(Bundle bundle) {
        context.finishSpeech(bundle);
    }

    //methode untuk menghandle saat terjadi error
    @Override
    public void onError(int i) {
        String errorMessage = getErrorText(i);
        Log.i(LOG_TAG, "FAILED " + errorMessage);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {}

    @Override
    public void onEvent(int eventType, Bundle params) {}

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
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
}
