package com.agmail.followduck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DuckView extends View {

    public float bitmapX;
    public float bitmapY;

    public DuckView(Context context) {
        super(context);
        bitmapX = 600;
        bitmapY = 1000;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.duck);
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
        if (bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
