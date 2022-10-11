package com.example.a71;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class Patinson extends View {

    private final Paint paint = new Paint();
    private final Bitmap patinson;
    private int currentX;
    private int finalX;
    private final Patinson patinson1;

    private final Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (currentX < finalX) {
                currentX += 10;
                handler.sendEmptyMessageDelayed(0, 16);
                patinson1.invalidate(); //перерисовка
            }
            return true;
        }
    };
    private final Handler handler = new Handler(hc);

    public Patinson(Context context) {
        super(context);
        Resources res = this.getResources();
        patinson = BitmapFactory.decodeResource(res, R.drawable.man);
        finalX = currentX = 0;
        patinson1 = this;
    }

    public Patinson(Context context, float dipWidth, float dipHeight, int delay) {
        super(context);
        Resources res = this.getResources();
        //this.delay = delay;


        int width, height;
        width = LinearLayout.LayoutParams.MATCH_PARENT;
        height = (int) dipToPixels(context, dipHeight);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);

        patinson = getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.man), height, height);
        this.setLayoutParams(lp);

        patinson1 = this;
        currentX = 0;
    }

    public void startAnim() {
        currentX = 0;
        finalX = getWidth();
        handler.sendEmptyMessage(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        canvas.drawBitmap(patinson, currentX, 0, paint);
    }

    private float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
