package com.example.taskmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyDrawingView extends View {
    private Paint paint;
    private float startX, startY, endX, endY;
    private long startTime;
    private Handler handler;
    private Runnable drawRunnable;

    public MyDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(0xFF000000); // Siyah renk
        paint.setStrokeWidth(20); // Çizgi kalınlığı
        handler = new Handler();
        drawRunnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void drawLineWithAnimation(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        startTime = System.currentTimeMillis();
        handler.post(drawRunnable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long currentTime = System.currentTimeMillis();
        float elapsedTime = (currentTime - startTime) / 1000.0f; // Saniye cinsinden geçen süre
        if (elapsedTime < 3) { // 3 saniyeden az süre geçtiyse çizimi devam ettir
            float progress = elapsedTime / 3; // Çizim ilerleme yüzdesi
            float currentX = startX + (endX - startX) * progress;
            float currentY = startY + (endY - startY) * progress;
            canvas.drawLine(startX, startY, currentX, currentY, paint);
            handler.postDelayed(drawRunnable, 16); // 16ms (yaklaşık 60 FPS) aralıklarla çizimi güncelle
        } else { // 3 saniye tamamlandı
            canvas.drawLine(startX, startY, endX, endY, paint);
        }
    }
}
