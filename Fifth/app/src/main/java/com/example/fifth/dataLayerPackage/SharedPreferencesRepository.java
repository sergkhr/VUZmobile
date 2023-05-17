package com.example.fifth.dataLayerPackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SharedPreferencesRepository {
    private static final String MY_SHARED_FILE_NAME = "mySharedFile";
    private static final String APP_PREFERENCES_NICKNAME = "nickname";

    private static SharedPreferences mSettings; //probably not a good decision to make it static, but the best I could come up with

    public static void createSharedPreferences(@NonNull Activity activity, @NonNull Context context){
        mSettings = activity.getSharedPreferences(MY_SHARED_FILE_NAME, context.MODE_PRIVATE);
    }

    public static void saveToSharedPreferences(String text){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NICKNAME, text);
        editor.apply();
    }

    public static String readFromSharedPreferences(){
        String fromWhere = mSettings.toString();
        if (mSettings.contains(APP_PREFERENCES_NICKNAME)) {
            String info = fromWhere + "\n\n" + mSettings.getString(APP_PREFERENCES_NICKNAME, "");
            return info;
        }
        return fromWhere + "\n\n" + "No nickname saved";
    }
}
