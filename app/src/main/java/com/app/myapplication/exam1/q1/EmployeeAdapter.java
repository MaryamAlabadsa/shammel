package com.app.myapplication.exam1.q1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.R;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Data> list;
    int recource;

    public EmployeeAdapter(Context context, ArrayList<Data> list,int recource) {
        this.context = context;
        this.list = list;
        this.recource = recource;
    }

    @Override
    public int getCount() {
        return !list.isEmpty() ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(recource, null, false);
        }
        TextView name_tv= view.findViewById(R.id.name_tv);
        TextView salary_tv= view.findViewById(R.id.salary_tv);
        LinearLayout layout= view.findViewById(R.id.layout);
        name_tv.setText(list.get(i).getName());
        salary_tv.setText(list.get(i).getSalary()+"");

        if (list.get(i).getSalary()<1000)
            layout.setBackgroundColor(Color.GREEN);
        else
            layout.setBackgroundColor(Color.RED);

        return view;
    }
}
