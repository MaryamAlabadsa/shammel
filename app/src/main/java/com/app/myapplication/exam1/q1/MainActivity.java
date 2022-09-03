package com.app.myapplication.exam1.q1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name_et, salary_et;
    RadioButton all, less, more;
    Button insert_btn;
    DataBaseAccess dataBase_access;
    EmployeeAdapter adapter;
    ListView listView;
    ArrayList<Data> list;
    int chose_btn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_et = findViewById(R.id.name_et);
        salary_et = findViewById(R.id.salary_et);
        insert_btn = findViewById(R.id.insert_btn);
        listView = findViewById(R.id.list_view);
        all = findViewById(R.id.all_radio);
        less = findViewById(R.id.less_radio);
        more = findViewById(R.id.more_radio);

        list = new ArrayList<>();

//        setListView();
        getData();
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose_btn = 1;
                getData();
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose_btn = 2;
                getData();
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose_btn = 0;
                getData();
            }
        });


        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_et.getText().toString();
                String salary = salary_et.getText().toString();
                if (!name.isEmpty() & !salary.isEmpty()) {
                    Data data = new Data(name, Double.parseDouble(salary));
                    insertData(data);
                    name_et.clearFocus();
                    salary_et.clearFocus();
                    salary_et.setText("");
                    name_et.setText("");
                    getData();
                }
            }
        });


    }

    private void openDB() {
        dataBase_access = DataBaseAccess.getInstance(MainActivity.this);
        dataBase_access.open();
    }

    private void insertData(Data data) {
        openDB();
        Boolean d = dataBase_access.insertData(data);
        dataBase_access.close();
    }

    private void getData() {

        if (chose_btn == 0) {
            all.setChecked(true);
            getAllData();
        } else if (chose_btn == 1) {
            less.setChecked(true);
            getlessData();
        } else if (chose_btn == 2) {
            more.setChecked(true);
            getMoreData();
        }
        dataBase_access.close();

    }

    private void getAllData() {
        openDB();
        list = dataBase_access.getAllItems();
        setListView(list);
    }

    private void getlessData() {
        openDB();
        list = dataBase_access.getLess();
        setListView(list);
    }

    private void getMoreData() {
        openDB();
        list = dataBase_access.getMore();
        setListView(list);
    }

    private void setListView(ArrayList<Data> list) {
        adapter = new EmployeeAdapter(MainActivity.this, list, R.layout.layout_employee);
        listView.setAdapter(adapter);
    }
}