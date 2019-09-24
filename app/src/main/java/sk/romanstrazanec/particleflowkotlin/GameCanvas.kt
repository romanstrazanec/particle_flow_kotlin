package sk.romanstrazanec.particleflowkotlin

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import kotlin.random.Random

class GameCanvas(viewContext: Context) : View(viewContext) {
    private val paint = Paint()
    private val size = getWindowSize(viewContext)

    // design settings
    private val backgroundColor: Int = Color.BLACK
    private val particleColor: Int = Color.WHITE
    private val particleRadius: Float = 1f
    private val particleAmount: Int = 500

    // behaviour settings
    private val attractionSpeed: Float = 20f
    private val drag: Int = 250

    // object construction
    private val attractionPoint = AttractionPoint(size.x / 2, size.y / 2, attractionSpeed)
    private val particles: Array<Particle> = Array(particleAmount) {
        Particle(rndWidth(), rndHeight(), particleRadius, particleColor)
    }

    private fun getWindowSize(context: Context): PointF {
        val size = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
        return PointF(size.x.toFloat(), size.y.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        background(canvas)

        particles.forEach { particle ->
            attractionPoint.attract(particle)
            if (particle.dispersed) {
                val dx = particle.x - attractionPoint.x
                val dy = particle.y - attractionPoint.y
                if (dx * dx + dy * dy > drag * drag) particle.dispersed = false
            }
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
        paint.color = backgroundColor
        canvas?.drawRect(0f, 0f, size.x, size.y, paint)
    }

    private fun rndWidth() = Random.nextFloat() * size.x
    private fun rndHeight() = Random.nextFloat() * size.y
}
