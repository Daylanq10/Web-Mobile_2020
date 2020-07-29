package com.example.spinthebottle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mButton;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mButton = findViewById(R.id.login);
        mCreateBtn = findViewById(R.id.registerPage);

        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
    }
}