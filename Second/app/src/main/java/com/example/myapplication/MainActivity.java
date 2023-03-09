package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View buttonToLinear = findViewById(R.id.toLinearBtn);
        buttonToLinear.setOnClickListener(toLinearListener);

        View buttonToRelative = findViewById(R.id.toRelativeBtn);
        buttonToRelative.setOnClickListener(toRelativeListener);
    }

    View.OnClickListener toLinearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.linear);

            View buttonToMain = findViewById(R.id.linearBackBtn);
            buttonToMain.setOnClickListener(toMainListener);
        }
    };

    View.OnClickListener toRelativeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.relative);

            View buttonToMain = findViewById(R.id.relativeBackBtn);
            buttonToMain.setOnClickListener(toMainListener);
        }
    };

    View.OnClickListener toMainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_main);

            View buttonToLinear = findViewById(R.id.toLinearBtn);
            buttonToLinear.setOnClickListener(toLinearListener);

            View buttonToRelative = findViewById(R.id.toRelativeBtn);
            buttonToRelative.setOnClickListener(toRelativeListener);
        }
    };

}