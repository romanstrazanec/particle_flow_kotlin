package sk.romanstrazanec.particleflowkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import android.view.WindowManager

class Canvas(viewContext: Context) : View(viewContext) {
    private var paint = Paint()
    private var size = this.getWindowSize(viewContext)

    private fun getWindowSize(context: Context): Point {
        val size = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
        return size
    }

    override fun onDraw(canvas: Canvas?) {
        background(canvas)
        invalidate()
    }

    private fun background(canvas: Canvas?) {
        paint.color = Color.BLACK
        canvas?.drawRect(0F, 0F, size.x.toFloat(), size.y.toFloat(), paint)
    }
}