package com.agmail.logininterface;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        String[] strs = {"1号位", "2号位", "3号位", "4号位", "5号位"};
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strs);
        ListView mylistview = findViewById(R.id.listview1);
        mylistview.setAdapter(myadapter);
    }
}
