package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
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
                setTitle(R.string.numbers);
                resource = R.layout.numbers;
                break;
            case "coin":
                setTitle(R.string.coin);
                resource = R.layout.coin;
                break;
            case "roshambo":
                setTitle(R.string.roshambo);
                resource = R.layout.roshambo;
                break;
            case "cube":
                setTitle(R.string.cube);
                resource = R.layout.cube;
                break;
            case "colors":
                setTitle(R.string.colors);
                resource = R.layout.colors;
                break;
            case "letters":
                setTitle(R.string.letters);
                resource = R.layout.letters;
                break;
            case "passwords":
                setTitle(R.string.passwords);
                resource = R.layout.passwords;
                break;

            default:
                Toast.makeText(this, "error...", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        inflater.inflate(resource, root);

        //это после инфлетера, ведь он ищет по ид в ресурсе
        //спиннер длинны
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
                public void onNothingSelected(AdapterView<?> parent) { }
            };
            spinner.setOnItemSelectedListener(itemSelectedListener);
        }

        //спиннер языков
        if(key.equals("letters")) {
            Spinner spinner = findViewById(R.id.langSpinner);
            String[] language = {"English", "Русский"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, language);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    lang = (String)parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            };
            spinner.setOnItemSelectedListener(itemSelectedListener);
        }

        if(key.equals("cube")) {
            dices.addLevel(0, 6, ResourcesCompat.getDrawable(getResources(), R.drawable.dices, null));
        }
    }

    private void Animation(View view, String animName) {
        switch (animName) {
            case "flip":
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down_flip));
                break;
            case "zoom":
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom));
                break;
            case "coinFlip":
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.coin_flip));
                break;
            default:
                Log.e("SECONDARY. ERROR", "wrong animation: " + animName);
                return;
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

        Animation(result, "flip");
    }

    public void getCoin(View view) {
        ImageView result = findViewById(R.id.result);

        result.setImageBitmap(Randomness.getRandom(0, 1) == 1
                ?
                ((BitmapDrawable) getDrawable(R.drawable.coin1)).getBitmap()
                :
                ((BitmapDrawable) getDrawable(R.drawable.coin2)).getBitmap());

        Animation(result, "coinFlip");
    }

    int passwordLen = 6;
    public void getPassword(View view) {
        boolean[] settings = {
                ((CheckBox)findViewById(R.id.checkbox1)).isChecked(),//заглавные
                ((CheckBox)findViewById(R.id.checkbox2)).isChecked(),//прописные
                ((CheckBox)findViewById(R.id.checkbox3)).isChecked(),//спец.
                ((CheckBox)findViewById(R.id.checkbox4)).isChecked()//цифры
        };

        TextView result = findViewById(R.id.result);
        result.setText(Randomness.getPassword(passwordLen, settings));

        Animation(result, "flip");
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

    String lang = "English";
    public void getLetter(View view) {
        TextView result = findViewById(R.id.result);

        result.setText(Randomness.getLetter(lang));

        Animation(result, "flip");
    }

    public void getRoshambo(View view) {
        ImageView result = findViewById(R.id.result);

        Bitmap bitmap;

        switch (Randomness.getRandom(1,3)) {
            case 1:
                bitmap = ((BitmapDrawable) getDrawable(R.drawable.rock)).getBitmap();
                break;
            case 2:
                bitmap = ((BitmapDrawable) getDrawable(R.drawable.scissors)).getBitmap();
                break;
            case 3:
                bitmap = ((BitmapDrawable) getDrawable(R.drawable.paper)).getBitmap();
                break;
            default:
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
                return;
        }

        result.setImageBitmap(bitmap);
        Animation(result, "zoom");
    }

    LevelListDrawable dices = new LevelListDrawable();
    public void getCube(View view) {
        ImageView result = findViewById(R.id.result);

        dices.setLevel(0);
        result.setImageDrawable(dices.getCurrent());

        dices.setLevel(Randomness.getRandom(1, 6));
        result.setImageDrawable(dices.getCurrent());

        Animation(result, "zoom");
    }
}
