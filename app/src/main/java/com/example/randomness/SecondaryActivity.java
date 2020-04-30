package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        String key = Objects.requireNonNull(getIntent().getExtras()).getString("key");

        Toast.makeText(this, key, Toast.LENGTH_LONG).show();
    }


}
