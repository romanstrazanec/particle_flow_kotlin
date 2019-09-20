package sk.romanstrazanec.particleflowkotlin

import kotlin.math.sqrt

class AttractionPoint(var x: Float, var y: Float) {
    fun moveTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    private fun distance(p: Particle): Float {
        val dx = p.x - x
        val dy = p.y - y
        return sqrt(dx * dx + dy * dy)
    }

    fun attract(p: Particle, attractionSpeed: Float) {
        val f = attractionSpeed / (distance(p) + 1)
        p.x += f * (x - p.x)
        p.y += f * (y - p.y)
    }
}
