package com.example.login_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class HomeActivity extends Activity {
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login=findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                {
                    Toast.makeText(HomeActivity.this, "Return to Main Screen", Toast.LENGTH_LONG).show();
                    Intent redirect = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(redirect);
                }
            }
        });
    }
}