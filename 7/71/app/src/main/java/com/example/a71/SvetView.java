package com.example.a71;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class SvetView extends View {

    private static final int TIME1 = 2700;
    private static final int TIME2 = 700;
    private static final int TIME3 = 3200;
    private static final int TIME4 = 700;

    private final Paint paint = new Paint();
    private final SvetView tlv;
    private int state = 1;

    private final Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1:
                    tlv.setState(1);
                    handler.sendEmptyMessageDelayed(2, SvetView.TIME1);
                    break;

                case 2:
                    tlv.setState(2);
                    handler.sendEmptyMessageDelayed(3, SvetView.TIME2);
                    break;

                case 3:
                    tlv.setState(3);
                    handler.sendEmptyMessageDelayed(4, SvetView.TIME3);
                    break;

                case 4:
                    tlv.setState(4);
                    handler.sendEmptyMessageDelayed(1, SvetView.TIME4);
                    break;

                default:
                    break;
            }
            tlv.invalidate();
            return true;
        }
    };
    private final Handler handler = new Handler(hc);

    private StateChangedCallback scc = null;


    public SvetView(Context context) {
        super(context);
        tlv = this;
        handler.sendEmptyMessage(1);
    }

    public SvetView(Context context, float dipWidth, float dipHeight, StateChangedCallback scc) {
        super(context);
        tlv = this;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dipToPixels(context, dipWidth), (int) dipToPixels(context, dipHeight));
        this.setLayoutParams(lp);
        this.scc = scc; //что бы патинсон пошел
        handler.sendEmptyMessage(1);
    }

    public void setState(int state) {
        this.state = state;
    }
    public void clear() {
        handler.removeMessages(1);
        handler.removeMessages(2);
        handler.removeMessages(3);
        handler.removeMessages(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float radius = height / 8.0F;
        float xPos = width / 2.0F;
        float yPos1 = radius;
        float yPos2 = radius * 3;
        float yPos3 = radius * 5;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        switch (state) {
            case 1:
                paint.setColor(Color.RED);
                canvas.drawCircle(xPos, yPos1, radius, paint);
                paint.setColor(Color.GRAY);
                canvas.drawCircle(xPos, yPos2, radius, paint);
                canvas.drawCircle(xPos, yPos3, radius, paint);
                break;
            case 2: // red|yellow|gray
                paint.setColor(Color.RED);
                canvas.drawCircle(xPos, yPos1, radius, paint);
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(xPos, yPos2, radius, paint);
                paint.setColor(Color.GRAY);
                canvas.drawCircle(xPos, yPos3, radius, paint);
                break;
            case 3: // gray|gray|green
                paint.setColor(Color.GRAY);
                canvas.drawCircle(xPos, yPos1, radius, paint);
                canvas.drawCircle(xPos, yPos2, radius, paint);
                paint.setColor(Color.GREEN);
                canvas.drawCircle(xPos, yPos3, radius, paint);
               scc.stateGreen();
                break;
            case 4: // gray|yellow|gray
                paint.setColor(Color.GRAY);
                canvas.drawCircle(xPos, yPos1, radius, paint);
                canvas.drawCircle(xPos, yPos3, radius, paint);
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(xPos, yPos2, radius, paint);
                break;
        }
    }

    private float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public interface StateChangedCallback {
        void stateGreen();
    }
}
