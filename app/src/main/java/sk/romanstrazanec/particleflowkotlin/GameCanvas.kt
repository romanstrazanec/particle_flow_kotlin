package sk.romanstrazanec.particleflowkotlin

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import kotlin.math.abs
import kotlin.random.Random

class GameCanvas(viewContext: Context) : View(viewContext) {
    private val paint = Paint()
    private val size = getWindowSize(viewContext)

    private val attractionPoint = AttractionPoint(size.x / 2, size.y / 2)
    private lateinit var particles: List<Particle>

    private fun getWindowSize(context: Context): PointF {
        val size = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
        return PointF(size.x.toFloat(), size.y.toFloat())
    }

    fun createParticles(amount: Int) {
        particles = Array(amount) { Particle(rndWidth(), rndHeight(), Color.WHITE) }.asList()
    }

    override fun onDraw(canvas: Canvas?) {
        background(canvas)

        particles.forEach { particle ->
            attractionPoint.attract(particle, size.x / 8F)

            if (abs(particle.x - attractionPoint.x) < 1
                && abs(particle.y - attractionPoint.y) < 1
            )
                particle.moveTo(rndWidth(), rndHeight())

            particle.draw(canvas, paint)
        }

        invalidate()
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean = when (event?.action) {
        MotionEvent.ACTION_DOWN -> {
            attractionPoint.moveTo(event.x, event.y)
            performClick()
        }
        else -> false
    }

    private fun background(canvas: Canvas?) {
        paint.color = Color.BLACK
        canvas?.drawRect(0F, 0F, size.x, size.y, paint)
    }

    private fun rndWidth() = Random.nextFloat() * size.x
    private fun rndHeight() = Random.nextFloat() * size.y
}
