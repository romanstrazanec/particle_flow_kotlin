package sk.romanstrazanec.particleflowkotlin

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF

class Particle(private val pos: PointF, private val color: Int) {
    fun move(dx: Float, dy: Float) {
        pos.x += dx
        pos.y += dy
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawCircle(pos.x, pos.y, 1F, paint)
    }
}