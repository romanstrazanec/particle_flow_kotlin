package sk.romanstrazanec.particleflowkotlin

import android.graphics.Canvas
import android.graphics.Paint

class Particle(var x: Float, var y: Float, val r: Float, private val color: Int) {
    fun moveTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun draw(canvas: Canvas?, paint: Paint) {
        paint.color = color
        canvas?.drawCircle(x, y, r, paint)
    }
}
