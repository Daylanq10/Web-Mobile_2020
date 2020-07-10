package com.example.vijaya.myorder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummaryActivity extends Activity {

    private Button GoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        GoBack = (Button) findViewById(R.id.exit);

        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}

