package com.app.myapplication.exam2.q1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseAcces {

    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static DataBaseAcces Instance;
    Cursor cursor;
    Context context;

    private DataBaseAcces(Context context) {
        this.context = context;
        this.openHelper = new DbUsers(context);
    }

    public static DataBaseAcces getInstance(Context context) {
        if (Instance == null) {
            Instance = new DataBaseAcces(context);
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

    public boolean Register(User user) {
        ContentValues values = new ContentValues();
        values.put(DbUsers.TABLE_CLN_FULL_NAME, user.getFull_name());
        values.put(DbUsers.TABLE_CLN_EMAIL, user.getEmail());
        values.put(DbUsers.TABLE_CLN_PASSWORD, user.getPassword());

        long res = database.insert(DbUsers.TABLE_NAME, null, values);
        return res != -1;
    }

    public ArrayList<User> getAllItems() {
        ArrayList<User> users = new ArrayList<>();
        cursor = database.rawQuery("select * from " + DbUsers.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DbUsers.TABLE_CLN_ID));
                @SuppressLint("Range") String full_name = cursor.getString(cursor.getColumnIndex(DbUsers.TABLE_CLN_FULL_NAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DbUsers.TABLE_CLN_EMAIL));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DbUsers.TABLE_CLN_PASSWORD));

                User u = new User(id, full_name, email, password);
                users.add(u);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    @SuppressLint("Range")
    public String login(User user) {
        String userName = "";

        cursor = database.rawQuery("select * from " + DbUsers.TABLE_NAME
                + " WHERE " + DbUsers.TABLE_CLN_EMAIL + " = '"
                + user.getEmail() + "' "
                + " AND " + DbUsers.TABLE_CLN_PASSWORD + " = '" + user.getPassword() + "' ", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            userName = cursor.getString(cursor.getColumnIndex(DbUsers.TABLE_CLN_FULL_NAME));
            cursor.moveToNext();
        }
        cursor.close();

        return userName;

    }

    public ArrayList<String> getAllUsersName(){
        ArrayList<String> names = new ArrayList<>();
        cursor = database.rawQuery("select * from " + DbUsers.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String full_name = cursor.getString(cursor.getColumnIndex(DbUsers.TABLE_CLN_FULL_NAME));
                names.add(full_name);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return names;
    }

}
