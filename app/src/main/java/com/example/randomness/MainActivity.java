package com.example.randomness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nothing(View view) {
        Toast.makeText(this, "nothing...", Toast.LENGTH_SHORT).show();
    }

    public void menuClick(View view) {
        LinearLayout ll = (LinearLayout)view;
        String tag = ll.getTag().toString();

        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("key", tag);
        startActivity(intent);
    }
}
