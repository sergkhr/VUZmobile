package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(message);
    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName4);
        String message = editText.getText().toString();
        intent.putExtra("message", message);
        setResult(RESULT_OK, intent);
        finish();
    }
}