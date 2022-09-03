package com.app.myapplication.exam2.q1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.myapplication.R;
import com.app.myapplication.exam1.q1.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email_et, password_et;
    Button login_btn, register_btn;
    DataBaseAcces dataBaseAccess;
    public static final String LOGIN_KEY="login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);

        login_btn.setOnClickListener(this::onClick);
        register_btn.setOnClickListener(this::onClick);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                openDB();
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                if (!email.isEmpty() & !password.isEmpty()) {
                    String b = dataBaseAccess.login(new User(email, password));
                    if (b.equals("")) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                LoginActivity.this);
                        alertDialog.setMessage("كلمة المرور او الايميل خاطئة");
                        alertDialog.setTitle("tttttttttttt");
                        alertDialog.show();
                    }
                    else{
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        intent.putExtra(LOGIN_KEY,b);
                        startActivity(intent);
                    }
                }
                dataBaseAccess.close();
                break;
            case R.id.register_btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

    private void openDB() {
        dataBaseAccess = DataBaseAcces.getInstance(this);
        dataBaseAccess.open();
    }
}