package com.example.bapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Database.db";
    public DBHelper(Context context) {
        super(context, "Database.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB){
        MyDB.execSQL("create Table login(username TEXT primary key,password TEXT,username2 TEXT,phonenumber NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists login");
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = MyDB.insert("login", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from login where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from login where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean insertuserdata(String username2,String phonenumber)
    {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username2", username2);
        contentValues.put("phonenumber", phonenumber);

        long result=MYDB.insert("login", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


   /* public Boolean updateuserdata(String username2, String username3, String phonenumber,String phonenumber1)
    {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username2", username2);
        contentValues.put("username2", username2);
        contentValues.put("phonenumber", phonenumber);
        contentValues.put("phonenumber1", phonenumber1);
        Cursor cursor = MYDB.rawQuery("Select * from login where username2 = ?", new String[]{username2});
        if (cursor.getCount() > 0) {
            long result = MYDB.update("login", contentValues, "username2=?", new String[]{username2});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String username2)
    {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor = MYDB.rawQuery("Select * from login where username2 = ?", new String[]{username2});
        if (cursor.getCount() > 0) {
            long result = MYDB.delete("login", "username2=?", new String[]{username2});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }*/

    public Cursor getdata ()
    {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor = MYDB.rawQuery("Select * from login", null);
        return cursor;

    }
}

