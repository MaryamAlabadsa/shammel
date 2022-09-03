package com.app.myapplication.exam1.q1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseAccess {

    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static DataBaseAccess Instance;
    Cursor cursor;
    Context context;

    private DataBaseAccess(Context context) {
        this.context=context;
        this.openHelper = new DB(context);
    }

    public static DataBaseAccess getInstance(Context context) {
        if (Instance == null) {
            Instance = new DataBaseAccess(context);
        }
        return Instance;
    }


    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    public boolean insertData(Data data) {
        ContentValues values = new ContentValues();
        values.put(DB.TABLE_CLN_NAME_NAME, data.getName());
        values.put(DB.TABLE_CLN_NAME_SALARY, data.getSalary());

        long res = database.insert(DB.TABLE_NAME, null, values);
        return res != -1;
    }

    public ArrayList<Data> getAllItems() {
        ArrayList<Data> carts = new ArrayList<>();
        cursor = database.rawQuery("select * from " + DB.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB.TABLE_CLN_NAME_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DB.TABLE_CLN_NAME_NAME));
                @SuppressLint("Range") double salary = cursor.getDouble(cursor.getColumnIndex(DB.TABLE_CLN_NAME_SALARY));

                Data c = new Data(id, name, salary);
                carts.add(c);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return carts;
    }
    public ArrayList<Data> getLess() {
        ArrayList<Data> carts = new ArrayList<>();
        cursor = database.rawQuery("select * from " + DB.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB.TABLE_CLN_NAME_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DB.TABLE_CLN_NAME_NAME));
                @SuppressLint("Range") double salary = cursor.getDouble(cursor.getColumnIndex(DB.TABLE_CLN_NAME_SALARY));
                Data c = new Data(id, name, salary);

                if (salary<1000){
                    carts.add(c);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return carts;
    }
    public ArrayList<Data> getMore() {
        ArrayList<Data> carts = new ArrayList<>();
        cursor = database.rawQuery("select * from " + DB.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB.TABLE_CLN_NAME_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DB.TABLE_CLN_NAME_NAME));
                @SuppressLint("Range") double salary = cursor.getDouble(cursor.getColumnIndex(DB.TABLE_CLN_NAME_SALARY));
                Data c = new Data(id, name, salary);

                if (salary>=1000){
                    carts.add(c);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return carts;
    }

}
