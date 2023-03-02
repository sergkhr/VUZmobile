package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreateError");
        Log.w(TAG, "onCreateWarning");
        Log.i(TAG, "onCreateInfo");
        Log.d(TAG, "onCreateDebug");
        Log.v(TAG, "onCreateVerbose");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onStartError");
        Log.w(TAG, "onStartWarning");
        Log.i(TAG, "onStartInfo");
        Log.d(TAG, "onStartDebug");
        Log.v(TAG, "onStartVerbose");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onResumeError");
        Log.w(TAG, "onResumeWarning");
        Log.i(TAG, "onResumeInfo");
        Log.d(TAG, "onResumeDebug");
        Log.v(TAG, "onResumeVerbose");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onPauseError");
        Log.w(TAG, "onPauseWarning");
        Log.i(TAG, "onPauseInfo");
        Log.d(TAG, "onPauseDebug");
        Log.v(TAG, "onPauseVerbose");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onStopError");
        Log.w(TAG, "onStopWarning");
        Log.i(TAG, "onStopInfo");
        Log.d(TAG, "onStopDebug");
        Log.v(TAG, "onStopVerbose");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onDestroyError");
        Log.w(TAG, "onDestroyWarning");
        Log.i(TAG, "onDestroyInfo");
        Log.d(TAG, "onDestroyDebug");
        Log.v(TAG, "onDestroyVerbose");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onRestartError");
        Log.w(TAG, "onRestartWarning");
        Log.i(TAG, "onRestartInfo");
        Log.d(TAG, "onRestartDebug");
        Log.v(TAG, "onRestartVerbose");
    }
}