package sk.romanstrazanec.particleflowkotlin

import android.graphics.Canvas
import android.graphics.Paint

class Particle(var x: Float, var y: Float, private val r: Float, private val color: Int) {
    var prevX: Float = x
    var prevY: Float = y
    val dx get() = x - prevX
    val dy get() = y - prevY
    var dispersed: Boolean = false

    fun moveTo(x: Float, y: Float) {
        prevX = this.x.also { this.x = x }
        prevY = this.y.also { this.y = y }
    }

    fun draw(canvas: Canvas?, paint: Paint) {
        paint.color = color
        canvas?.drawCircle(x, y, r, paint)
        canvas?.drawLine(x, y, prevX, prevY, paint)
    }
}
