package com.agmail.followduck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class FollowDuck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_duck);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.my_layout);
        final DuckView duck=new DuckView(this);
        duck.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                duck.bitmapX=event.getX();
                duck.bitmapY=event.getY();
                duck.invalidate();
                return true;
            }
        });
        frameLayout.addView(duck);
    }
}
