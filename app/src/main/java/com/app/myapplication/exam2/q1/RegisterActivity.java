package com.app.myapplication.exam2.q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.myapplication.R;

public class RegisterActivity extends AppCompatActivity {
    EditText email_et, password_et,fullname_et;
    Button register_btn;
    DataBaseAcces dataBaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname_et=findViewById(R.id.full_name_et);
        email_et=findViewById(R.id.email_et);
        password_et=findViewById(R.id.password_et);
        register_btn=findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                String full_name = fullname_et.getText().toString();
                if (!email.isEmpty() & !password.isEmpty()& !full_name.isEmpty()) {
                    dataBaseAccess=DataBaseAcces.getInstance(RegisterActivity.this);
                    dataBaseAccess.open();
                   boolean b= dataBaseAccess.Register(new User(full_name,email,password));
                    dataBaseAccess.close();
                    Toast.makeText(RegisterActivity.this, b+"", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            }
        });
    }
}