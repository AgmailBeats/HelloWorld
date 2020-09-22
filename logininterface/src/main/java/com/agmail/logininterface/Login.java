package com.agmail.logininterface;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 为登录按钮与注册按钮设置监听事件
        Button Login_Button = findViewById(R.id.login_button);
        Button Register_Button = findViewById(R.id.register_button);
        Login_Button.setOnClickListener(this);
        Register_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 新建数据库及连接数据库进行下一步操作
        MyDBOpenHelper mDBHewlper = new MyDBOpenHelper(Login.this);
        SQLiteDatabase db = mDBHewlper.getWritableDatabase();

        if (v.getId() == R.id.login_button) {
            EditText user_Text = findViewById(R.id.editText1);
            EditText password_Text = findViewById(R.id.editText2);
            String input_user = user_Text.getText().toString();
            String input_password = password_Text.getText().toString();

            Cursor mycursor = db.rawQuery("SELECT name,password FROM user WHERE name = ? AND password = ?",
                    new String[]{input_user, input_password});
            // 存在数据才返回true
            if (mycursor.moveToFirst()) {
                mycursor.close();
                Intent my_intent = new Intent(Login.this, Weather.class);
                startActivity(my_intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Login.this, "用户名或密码错误，登录失败", Toast.LENGTH_SHORT).show();
            }

        }

        if (v.getId() == R.id.register_button) {
            EditText user_Text = findViewById(R.id.editText1);
            EditText password_Text = findViewById(R.id.editText2);
            String input_user = user_Text.getText().toString();
            String input_password = password_Text.getText().toString();

            // 实例化常量值
            ContentValues cValue = new ContentValues();
            // 添加用户名
            cValue.put("name", input_user);
            // 添加密码
            cValue.put("password", input_password);
            // 调用insert()方法插入数据
            db.insert("user", null, cValue);

        }
    }

}


