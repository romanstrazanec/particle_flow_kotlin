package sk.romanstrazanec.particleflowkotlin

import android.os.Handler

class UpdateThread(private var updatingHandler: Handler) : Thread() {
    override fun run() {
        while (true) {
            try {
                Thread.sleep(50)
            } catch (e: Exception) {}
            updatingHandler.sendEmptyMessage(0)
        }
    }
}