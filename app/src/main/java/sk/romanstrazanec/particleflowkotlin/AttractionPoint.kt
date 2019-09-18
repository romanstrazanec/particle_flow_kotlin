package sk.romanstrazanec.particleflowkotlin

class AttractionPoint(var x: Float, var y: Float) {
    fun moveTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun attract(p: Particle) {
        p.x += x.compareTo(p.x)
        p.y += y.compareTo(p.y)
    }
}
