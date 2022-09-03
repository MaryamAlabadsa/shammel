package com.app.myapplication.exam2.q1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbUsers extends SQLiteOpenHelper {
    public static final String DB_NAME="USERS";
    public static final String TABLE_NAME="user";
    public static final String TABLE_CLN_ID="id";
    public static final String TABLE_CLN_FULL_NAME="full_name";
    public static final String TABLE_CLN_EMAIL="email";
    public static final String TABLE_CLN_PASSWORD="password";
    public static final  int DB_VERSION=4;


    public DbUsers(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateUserTable="create table "
                + TABLE_NAME +" ( "
                + TABLE_CLN_ID +" Integer primary key autoincrement ,"
                + TABLE_CLN_EMAIL +" text not null UNIQUE ,"
                + TABLE_CLN_FULL_NAME +" text not null ,"
                + TABLE_CLN_PASSWORD +" text not null "+")";
        sqLiteDatabase.execSQL(sqlCreateUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" drop table if exists "+TABLE_NAME+"");
        onCreate(sqLiteDatabase);

    }
}
