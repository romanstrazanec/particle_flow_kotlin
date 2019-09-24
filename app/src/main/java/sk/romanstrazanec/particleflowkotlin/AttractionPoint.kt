package sk.romanstrazanec.particleflowkotlin

class AttractionPoint(var x: Float, var y: Float, private val attractionSpeed: Float) {
    fun moveTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun attract(p: Particle) {
        val rx = x - p.x
        val ry = y - p.y
        val distance = rx * rx + ry * ry

        if (distance < 0.5f) p.dispersed = true

        val force = (if (p.dispersed) -1f else 1f) * attractionSpeed / distance
        p.moveTo(p.x + force * rx, p.y + force * ry)

        if (rx * p.dx < 0f && ry * p.dy < 0f) p.dispersed = true
    }
}
