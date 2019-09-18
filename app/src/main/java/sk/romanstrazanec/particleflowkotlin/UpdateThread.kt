package sk.romanstrazanec.particleflowkotlin

import android.os.Handler

class UpdateThread(private val updatingHandler: Handler) : Thread() {
    override fun run() {
        while (true) {
            try {
                sleep(50)
            } catch (e: Exception) {}
            updatingHandler.sendEmptyMessage(0)
        }
    }
}