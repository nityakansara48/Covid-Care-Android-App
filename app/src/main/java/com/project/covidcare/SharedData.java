package com.project.covidcare;

public class SharedData {

    public static String email;
    public static String name;

    public SharedData(String userEmail, String userName){
        email = userEmail;
        name = userName;
    }

    public static String getEmail(){
        return email;
    }

    public static  String getName(){
        return name;
    }

    public static void resetData(){
        email = "";
        name = "";
    }
}
