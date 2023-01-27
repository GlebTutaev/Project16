package com.example.project16;

import static com.example.project16.UserStaticInfo.LOGIN;
import static com.example.project16.UserStaticInfo.PASSWORD;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;

public class Transform {

    public static final String APP_PREFERENCES = "mysettings";

    public static void SaveUser(SharedPreferences sp, String login, String passwword){

        SharedPreferences.Editor e = sp.edit();
        e.putString(LOGIN, login);
        e.putString(PASSWORD, passwword);
        e.apply();
    }
    public static Boolean StringNoNull(String string){


        if(string==null) return false;
        else return string.length() !=0;
    }


    public static void Vibrate(Context context){


        long millis = 1000L;
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator.hasVibrator()){

            vibrator.vibrate(millis);
        }
    }

    public static int parsIntOrDefault(String whatParse, int defaultValue){
        int parse;
        try{
            parse = Integer.parseInt(whatParse);
        }
        catch (Exception NumberFormatException){
            parse = defaultValue;
        }
        return parse;
    }
}
