package com.example.project16;

public class Transform {
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
