package sk.romanstrazanec.particleflowkotlin.javacode;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GameCanvas extends View {
    private final Paint paint;
    private final PointF size;

    private final AttractionPoint attractionPoint;
    private final List<Particle> particles;

    @TargetApi(Build.VERSION_CODES.N)
    GameCanvas(Context viewContext) {
        super(viewContext);
        paint = new Paint();
        size = getWindowSize(viewContext);
        attractionPoint = new AttractionPoint(size.x / 2, size.y / 2);
        particles = IntStream.range(0, 10000)
                .mapToObj(i -> new Particle(rndWidth(), rndHeight(), Color.WHITE)).collect(Collectors.toList());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private PointF getWindowSize(Context context) {
        final Point size = new Point();
        ((WindowManager) Objects.requireNonNull(context.getSystemService(Context.WINDOW_SERVICE)))
                .getDefaultDisplay().getSize(size);
        return new PointF(size.x, size.y);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onDraw(Canvas canvas) {
        if (canvas == null)
            return;
        background(canvas);

        particles.forEach(particle -> {
            attractionPoint.attract(particle);

            if (Math.abs(particle.x - attractionPoint.x) < 1
                    && Math.abs(particle.y - attractionPoint.y) < 1)
                particle.moveTo(rndWidth(), rndHeight());

            particle.draw(canvas, paint);
        });

        invalidate();
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event == null)
            return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                attractionPoint.moveTo(event.getX(), event.getY());
                performClick();
                return true;
            default:
                return false;
        }
    }

    private void background(Canvas canvas) {
        paint.setColor(Color.BLACK);
        if (canvas != null)
            canvas.drawRect(0F, 0F, size.x, size.y, paint);
    }

    private float rndWidth() {
        return new Random().nextFloat() * size.x;
    }

    private float rndHeight() {
        return new Random().nextFloat() * size.y;
    }
}
