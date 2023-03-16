package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View buttonToLinear = findViewById(R.id.toLinearBtn);
        buttonToLinear.setOnClickListener(toLinearListener);

        //View buttonToRelative = findViewById(R.id.toRelativeBtn);
        //buttonToRelative.setOnClickListener(toRelativeListener);
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

    public void logClickHandler(View view) {
        Log.i("MainActivity", "Button clicked!");
        setContentView(R.layout.relative);

        View buttonToMain = findViewById(R.id.relativeBackBtn);
        buttonToMain.setOnClickListener(toMainListener);
    }


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        setContentView(R.layout.activity_main);

                        Intent data = result.getData();
                        String message = data.getStringExtra("message");
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(message);
                    }
                }
    });
    public void newActivityActivate(View view){
        Intent intent = new Intent(this, NewActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName3);
        intent.putExtra("message", editText.getText().toString());


        activityResultLauncher.launch(intent);
    }


}