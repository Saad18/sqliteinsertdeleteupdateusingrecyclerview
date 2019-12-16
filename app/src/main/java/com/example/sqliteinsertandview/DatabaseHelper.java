package com.example.sqliteinsertandview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "User.db";
    private static String TABLE_NAME = "User";
    static String COL_ID = "Id";
    static String COL_NAME = "Name";
    static String COL_AGE = "Age";
    private static  int VERSION = 1;
    private static String createTable = "create table "+TABLE_NAME+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Age TEXT)";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

     //Insert Data
    long insertData(String name, String age){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

        return id;

    }
    //Cursor that use to display data
    Cursor showData(){
        String show_all = "select * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(show_all,null);
    }
    //Delete data
    void deleteData(int id){
        getWritableDatabase().delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
     //Update the existing data
    boolean updateValues(String id, String name, String age){
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        values.put(COL_ID,id);
        values.put(COL_NAME,name);
        values.put(COL_AGE,age);
        sqLiteDatabase.update(TABLE_NAME,values,"id=?",new String[]{id});
        sqLiteDatabase.close();

        return true;
    }


}
