package com.example.a73;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class T3View extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private final Touch touch = new Touch();

    @SuppressLint("ClickableViewAccessibility")
    public T3View(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.setOnTouchListener((view, motionEvent) -> {
            int action = motionEvent.getAction();
            if (action == 0) {
                touch.isTouched = true;
            } else if (action == 1) {
                touch.isTouched = false;
            }
            return true;
        });
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(surfaceHolder, touch);
        drawThread.setRunning(true);
        drawThread.start();
    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static class DrawThread extends Thread {
        private boolean running = false;
        private final SurfaceHolder surfaceHolder;
        private final Touch touch;

        private int stage = 1;
        private int step = 0;
        private final int S1CNT = 100;
        private final int S2CNT = 180;
        private final Paint paint = new Paint();

        public DrawThread(SurfaceHolder surfaceHolder, Touch touch) {
            this.surfaceHolder = surfaceHolder;
            this.touch = touch;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas == null)
                        continue;
                    draw(canvas);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
        private void draw(Canvas canvas) throws InterruptedException{
            if (touch.isTouched && isFinal()) {
                drawStageStep(canvas, 3, 1);
                Thread.sleep(150);
                return;
            }
            if (!touch.isTouched && isAtStart()) {
                drawStageStep(canvas, 1, 0);
                Thread.sleep(50);
                return;
            }

            if (touch.isTouched) {
                drawStageStep(canvas, stage, step);
                step++;
                if (stage == 1 && step == S1CNT) {
                    stage = 2;
                    step = 0;
                } else if (stage == 2 && step == S2CNT) {
                    stage = 3;
                    step = 0;
                }
            } else {
                drawStageStep(canvas, stage, step);
                step--;
                if (stage == 3 && step == 0) {
                    stage = 2;
                    step = S2CNT;
                } else if (stage == 2 && step == 0) {
                    stage = 1;
                    step = S1CNT;
                }
            }

            Thread.sleep(17);
        }

        private void drawStageStep(Canvas canvas, int stage, int step) {
            canvas.drawColor(Color.WHITE);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            float x, y;
            String text = "Hello, world!";
            switch (stage) {
                case 1:
                    paint.setColor(Color.BLUE);
                    paint.setTextSize(80);
                    canvas.drawText(text,
                            width / 2.0F - paint.measureText(text) / 2.0F,
                            (float) step * height / (S1CNT + 20) + paint.getTextSize(),
                            paint);
                    break;

                case 2:
                    paint.setColor(Color.BLUE);
                    paint.setTextSize(80);
                    canvas.save();
                    x = width / 2.0F;
                    y = (float) S1CNT * height / (S1CNT + 20) + paint.getTextSize();
                    canvas.rotate(step, x, y);
                    float textDel2 = paint.measureText(text) / 2.0F;
                    canvas.drawText(text, x - textDel2, y, paint);
                    canvas.restore();
                    break;

                case 3:
                    if (step == 0)
                        paint.setColor(Color.BLUE);
                    else
                        paint.setColor(Color.MAGENTA);
                    paint.setTextSize(80);
                    canvas.save();
                    x = width / 2.0F;
                    y = (float) S1CNT * height / (S1CNT + 20) + paint.getTextSize();
                    canvas.rotate(180, x, y);
                    float textDel3 = paint.measureText(text) / 2.0F;
                    canvas.drawText(text, x - textDel3, y, paint);
                    canvas.restore();
                    break;
            }
        }

        private boolean isFinal() {
            if (stage < 3)
                return false;
            else
                return step != 0;
        }

        private boolean isAtStart() {
            if (stage > 1)
                return false;
            else
                return step == 0;
        }
    }

    private static class Touch {
        public Boolean isTouched = false;
    }
}

