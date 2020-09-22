package com.agmail.logininterface;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather extends AppCompatActivity {

    private static final int SET = 1;

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET:
                    String[] str = (String[]) msg.obj;
                    ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Weather.this, android.R.layout.simple_expandable_list_item_1, str);
                    ListView mylistview = findViewById(R.id.listview1);
                    mylistview.setAdapter(myadapter);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        new WeatherThread().start();

    }

    class WeatherThread extends Thread {
        @Override
        public void run() {
            String weatherurl = "https://www.tianqiapi.com/api?version=v6&appid=99291723&appsecret=vZe4Ehso";
            try {
                String[] getstr = GetData(weatherurl);
                Message msg = Weather.this.handler.obtainMessage(Weather.SET, getstr);
                Weather.this.handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String[] GetData(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求URL失败");
        }

        // 打开数据输入流
        InputStream ins = conn.getInputStream();
        InputStreamReader inst = new InputStreamReader(conn.getInputStream());
        // 为输出创建BufferReader
        BufferedReader buffer = new BufferedReader(inst);
        StringBuffer JsonData = new StringBuffer();
        // 使用循环来读取获得的数据
        String inputline = null;
        while ((inputline = buffer.readLine()) != null) {
            JsonData.append(inputline);
        }
        String sJsonData = JsonData.toString();
        JSONObject myjsonobject = new JSONObject(sJsonData);
        String[] strs = {"城市名：" + myjsonobject.getString("city"), "当前温度：" + myjsonobject.getString("tem")};
        buffer.close();
        conn.disconnect();
        return strs;
    }
}
