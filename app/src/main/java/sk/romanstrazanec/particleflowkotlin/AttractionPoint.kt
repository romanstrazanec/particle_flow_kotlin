package sk.romanstrazanec.particleflowkotlin

import android.graphics.PointF

class AttractionPoint(private val pos: PointF) {
    fun moveTo(newPos: PointF) {
        pos.x = newPos.x
        pos.y = newPos.y
    }
}