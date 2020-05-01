package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
                resource = R.layout.colors;
                break;
            case "letters":
                break;
            case "passwords":
                resource = R.layout.passwords;
                break;

            default:
                Toast.makeText(this, "error...", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        inflater.inflate(resource, root);

        if(key.equals("passwords")) {
            Spinner spinner = findViewById(R.id.lenSpinner);
            String[] length = {"6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, length);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    // Получаем выбранный объект
                    String item = (String)parent.getItemAtPosition(position);
                    passwordLen = Integer.parseInt(item);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner.setOnItemSelectedListener(itemSelectedListener);
        }
    }

    public void getNumber(View view) {
        TextView result = findViewById(R.id.result);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Button)view).getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

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

    int passwordLen = 6;
    public void getPassword(View view) {
        boolean[] settings = {
                ((CheckBox)findViewById(R.id.checkbox1)).isChecked(),//заглавные
                ((CheckBox)findViewById(R.id.checkbox2)).isChecked(),//прописные
                ((CheckBox)findViewById(R.id.checkbox3)).isChecked(),//спец.
                ((CheckBox)findViewById(R.id.checkbox4)).isChecked()//цифры
        };

        ((TextView)findViewById(R.id.result)).setText(Randomness.getPassword(passwordLen, settings));
    }

    public void getColor(View view) {
        ConstraintLayout colorRoot = findViewById(R.id.colorRoot);
        TextView colorCode = findViewById(R.id.colorCode);

        int r = Randomness.getRandom(0, 255);
        int g = Randomness.getRandom(0, 255);
        int b = Randomness.getRandom(0, 255);

        int color = Color.rgb(r, g, b);
        int contrast = Color.rgb(255 - r, 255 - g, 255 - b);

        colorRoot.setBackgroundColor(color);

        String code = "#" + Integer.toHexString(color).substring(2, 8).toUpperCase();
        colorCode.setText(code);
        colorCode.setTextColor(contrast);
    }

}
