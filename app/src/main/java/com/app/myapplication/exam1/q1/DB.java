package com.app.myapplication.exam1.q1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DB extends SQLiteOpenHelper {
    public static final String DB_NAME = "emp.db";
    public static final String TABLE_NAME = "employer";
    public static final String TABLE_CLN_NAME_ID = "id";
    public static final String TABLE_CLN_NAME_NAME = "name";
    public static final String TABLE_CLN_NAME_SALARY = "salary";
    public static final int  DB_VERSION = 2;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlEmployerTable = "create table "
                + TABLE_NAME + " ("
                + TABLE_CLN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TABLE_CLN_NAME_NAME + " TEXT NOT NULL ,"
                + TABLE_CLN_NAME_SALARY + " TEXT NOT NULL"
                + " )";
        db.execSQL(sqlEmployerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME + "");
        onCreate(db);
    }
//    public boolean insertProduct(String name, String catgory,
//                                 double price, String
//                                         shopNameString, String productImageString) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(productName, name);
//        values.put(productCatgory, catgory);
//        values.put(productPrice, price);
//        values.put(shopName, shopNameString);
//        values.put(productImage, productImageString);
//        Long id = db.insert(productTable, null, values);
//        db.close();
//        if (id == -1)
//            return false;
//        else
//            return true;
//    }
}
