package sk.romanstrazanec.particleflowkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import android.view.WindowManager

class GameCanvas(viewContext: Context) : View(viewContext) {
    private val paint = Paint()
    private val size = this.getWindowSize(viewContext)

    private fun getWindowSize(context: Context): Point {
        val size = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
        return size
    }

    fun update() {}

    override fun onDraw(canvas: Canvas?) {
        this.background(canvas)
        this.invalidate()
    }

    private fun background(canvas: Canvas?) {
        paint.color = Color.BLACK
        canvas?.drawRect(0F, 0F, size.x.toFloat(), size.y.toFloat(), paint)
    }
}