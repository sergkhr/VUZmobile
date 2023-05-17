package com.example.fifth.models;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExternalStorageModel {
    private static final String EXTERNAL_STORAGE_FILE_NAME = "myFile.txt";

    public static void saveToFile(String data, Activity activity){
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            File externalStorageFile = new File(Environment.getExternalStorageDirectory(), EXTERNAL_STORAGE_FILE_NAME);
            try {
                externalStorageFile.createNewFile();
                try {
                    FileWriter writer = new FileWriter(externalStorageFile);
                    writer.write(data);
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }


            //write to file
            try {
                FileWriter writer = new FileWriter(externalStorageFile);
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Log.e("ERROR", "No permission");
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            activity.startActivity(intent);
        }
    }

    public static String readFromFile(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            File externalStorageFile = new File(Environment.getExternalStorageDirectory(), EXTERNAL_STORAGE_FILE_NAME);
            if (externalStorageFile.exists()) {
                try {
                    String fromWhere = externalStorageFile.toString();
                    FileReader reader = new java.io.FileReader(externalStorageFile);
                    Scanner scanner = new java.util.Scanner(reader);
                    String text = fromWhere + "\n\n" + scanner.nextLine();
                    reader.close();
                    return text;
                } catch (Exception e) {
                    Log.e("ERROR", "Error reading file");
                }
            }
        }
        else{
            Log.e("ERROR", "No permission");
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            activity.startActivity(intent);
        }
        return "";
    }
}
