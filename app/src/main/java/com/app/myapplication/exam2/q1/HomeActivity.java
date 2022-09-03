package com.app.myapplication.exam2.q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.myapplication.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView tv;
    ListView listView;
    DataBaseAcces dataBaseAcces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String user_name = getIntent().getStringExtra(LoginActivity.LOGIN_KEY);
        tv = findViewById(R.id.user_name_tv);
        listView = findViewById(R.id.list_view);
        tv.setText(user_name + "");

        dataBaseAcces = DataBaseAcces.getInstance(this);
        dataBaseAcces.open();
        ArrayList<String> names = new ArrayList<>();
        names = dataBaseAcces.getAllUsersName();
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

    }
}