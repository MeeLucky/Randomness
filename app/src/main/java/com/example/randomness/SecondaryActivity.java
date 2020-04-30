package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

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

    public void getNumber(View view) {
        TextView result = findViewById(R.id.result);

        int min = Integer.parseInt(((EditText)findViewById(R.id.minET)).getText().toString());
        int max = Integer.parseInt(((EditText)findViewById(R.id.maxET)).getText().toString());

        result.setText(String.valueOf(Randomness.getRandom(min, max)));
    }

    public void getCoin(View view) {
        ImageView result = findViewById(R.id.result);

        result.setImageBitmap(Randomness.getRandom(0, 1) == 1
                ?
                ((BitmapDrawable) getDrawable(R.drawable.coin1)).getBitmap()
                :
                ((BitmapDrawable) getDrawable(R.drawable.coin2)).getBitmap());
    }



}
