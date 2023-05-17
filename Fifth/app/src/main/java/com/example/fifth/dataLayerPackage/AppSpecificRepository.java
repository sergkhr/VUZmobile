package com.example.fifth.dataLayerPackage;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class AppSpecificRepository {
    private static final String APP_SPECIFIC_FILE_NAME = "myFile.txt";

    public static void writeToFile(String text, Context context) {
        File appSpecificFile = new File(context.getFilesDir(), APP_SPECIFIC_FILE_NAME);

        if(!appSpecificFile.exists()){
            try {
                appSpecificFile.createNewFile();
            } catch (Exception e) {
                Log.e("ERROR", "Error creating file");
            }
        }

        //write to file
        try {
            FileWriter writer = new FileWriter(appSpecificFile);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e("ERROR", "Error writing to file");
        }
    }

    public static String readFromFile(Context context){
        File appSpecificFile = new File(context.getFilesDir(), APP_SPECIFIC_FILE_NAME);
        if(appSpecificFile.exists()){
            try {
                String fromWhere = appSpecificFile.toString();
                FileReader reader = new FileReader(appSpecificFile);
                Scanner scanner = new Scanner(reader);
                String text = fromWhere + "\n\n" + scanner.nextLine();
                reader.close();
                return text;
            } catch (Exception e) {
                Log.e("ERROR", "Error reading file");
            }
        }
        return "";
    }
}
