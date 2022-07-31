package com.project.covidcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DBConnection extends SQLiteOpenHelper {

    public static final String DBName = "CovidCare.db";

    public DBConnection(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE user(email TEXT primary key, password TEXT, name TEXT, contact TEXT, address TEXT, city TEXT, pinCode TEXT, covidStatus TEXT, covidDate DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user");
    }

    public Boolean insertData(String email, String password, String name, String contact, String address, String city, String pinCode, String covidStatus, String covidDate){
        System.out.println("Inserted Begin.");
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password",password);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("address", address);
        contentValues.put("city", city);
        contentValues.put("pinCode", pinCode);
        contentValues.put("covidStatus", covidStatus);
        contentValues.put("covidDate", covidDate);
        long result =  MyDB.insert("user", null, contentValues);
        System.out.println("Inserted End.");
        if (result==-1) return false;
        else {
            System.out.println("Record Inserted.");
            return true;
        }
    }

    public Boolean checkUserName(String email){
        System.out.println("Inside Check Username.");
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from user where email = ?", new String[] {email});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserNamePassword(String email, String password){
        System.out.println("Inside Check Username & Password.");
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from user where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public String getUsername(String email){
        String userName = "";
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select name from user where email = ?", new String[] {email});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                userName = cursor.getString(cursor.getColumnIndex("name"));
                cursor.moveToNext();
            }
        }
        return userName;
    }

    public List<String> getAllData(String email){
        List<String> userRecords = new ArrayList<String>();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from user where email = ?", new String[] {email});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String userEmail = cursor.getString(cursor.getColumnIndex("email"));
                userRecords.add(userEmail);
                String userName = cursor.getString(cursor.getColumnIndex("name"));
                userRecords.add(userName);
                String userContact = cursor.getString(cursor.getColumnIndex("contact"));
                userRecords.add(userContact);
                String userAddress = cursor.getString(cursor.getColumnIndex("address"));
                userRecords.add(userAddress);
                String userCity = cursor.getString(cursor.getColumnIndex("city"));
                userRecords.add(userCity);
                String userPinCode = cursor.getString(cursor.getColumnIndex("pinCode"));
                userRecords.add(userPinCode);
                String userCovidStatus = cursor.getString(cursor.getColumnIndex("covidStatus"));
                userRecords.add(userCovidStatus);
                String userCovidDate = cursor.getString(cursor.getColumnIndex("covidDate"));
                userRecords.add(userCovidDate);
                cursor.moveToNext();
            }
        }
        return userRecords;
    }

    public Boolean updateCovidStatus(String email, String covidStatus, String covidDate){
        ContentValues values = new ContentValues();
        values.put("covidStatus",covidStatus);
        values.put("covidDate", covidDate);
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.update("user", values, "email = ?",new String[] {email});
        return true;
    }

    public String resetPassword(String email, String pinCode, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select pinCode from user where email = ?", new String[] {email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            if (cursor.getString(cursor.getColumnIndex("pinCode")).equals(pinCode)) {
                ContentValues values = new ContentValues();
                values.put("password",password);
                MyDB.update("user", values, "email = ?",new String[] {email});
                return "Password Changed";
            } else{
                return "Invalid PinCode";
            }
        }else{
            return "Invalid Email ID";
        }
    }
}
