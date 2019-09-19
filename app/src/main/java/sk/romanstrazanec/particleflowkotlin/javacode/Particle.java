package sk.romanstrazanec.particleflowkotlin.javacode;

import android.graphics.Canvas;
import android.graphics.Paint;

class Particle {
    float x, y;
    private int color;

    Particle(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        if (canvas != null)
            canvas.drawCircle(x, y, 1F, paint);
    }
}
