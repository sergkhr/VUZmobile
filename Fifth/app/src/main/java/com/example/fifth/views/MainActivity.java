package com.example.fifth.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fifth.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}