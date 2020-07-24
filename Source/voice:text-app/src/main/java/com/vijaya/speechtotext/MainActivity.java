package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private TextView mOutput;
    private TextToSpeech textToSpeech;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SETUP FOR EDITOR
        preferences = getSharedPreferences(PREFS,0);
        editor = preferences.edit();
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mOutput = (TextView) findViewById(R.id.output);
        ImageButton mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        // SETUP TEXTTOSPEACH OBJECT
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // WANTED TO DO CANADIAN
                    int text = textToSpeech.setLanguage(Locale.CANADA);
                    // MY NAME IS _____
                    speak("Hello, what is your name?");
                } }});
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SWITCH CASE IF REQUEST CODE CORRECT
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String inSpeech = res.get(0);
                    recognition(inSpeech);
                }
                break;
            }
        }
    }

    private void speak(String text){
        // TEXT TO SPEECH
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void recognition(String text){ // ACTIVATE WITH VOICE
        // CATCHES KEY PHRASES
        Log.e("Speech","" + text);
        String[] speech = text.split(" ");
        // SAVES NAME
        if(text.contains("my name is ")){
            String name = speech[speech.length-1];
            editor.putString(NAME,name).apply(); // STORE IN EDITOR
            speak("How are you today? " + preferences.getString(NAME, null));
            String out = "How are you today, " + preferences.getString(NAME, null) + "?";
            mOutput.setText(out);
        }
        // NOT FEELING GOOD
        else if(text.contains("I'm not feeling good what should I do")){
            speak("I can understand. Please tell your symptoms in short.");
            String out = "I can understand. Please tell your symptoms in short.";
            mOutput.setText(out);
        }
        // THANK YOU
        else if(text.contains("thank you my medical assistant")){
            speak("Thank you too," + preferences.getString(NAME, null) + ", take care.");
            String out = "Thank you too," + preferences.getString(NAME, null) + ", take care.";
            mOutput.setText(out);
        }
        // TIME
        else if(text.contains("what time is it")) {
            // GET DATE
            SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm");
            // DATE OBJECT
            Date now = new Date();
            String[] strDate = sdfDate.format(now).split(":");
            speak("The time is " + sdfDate.format(now));
            String out = "The time is " + sdfDate.format(now);
            mOutput.setText(out);
        }
        // MEDICINE
        else if(text.contains("what medicines should I take")){
            speak("think you have a fever. Please take this medicine.");
            String out = "Think you have a fever. Please take this medicine.";
            mOutput.setText(out);
        }
    }
}