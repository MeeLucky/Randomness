package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.Objects;
import java.util.zip.Inflater;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);


        LayoutInflater inflater = getLayoutInflater();
        ConstraintLayout root = findViewById(R.id.root);

        String key = Objects.requireNonNull(getIntent().getExtras()).getString("key");
        int resource = 0;

        switch (key) {
            case "numbers":
                resource = R.layout.numbers;
                break;
            case "coin":
                resource = R.layout.coin;
                break;
            case "roshambo":
                break;
            case "cube":
                break;
            case "colors":
                break;
            case "letters":
                break;
            case "passwords":
                break;

            default:
                Toast.makeText(this, "error...", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        inflater.inflate(resource, root);
    }


}
