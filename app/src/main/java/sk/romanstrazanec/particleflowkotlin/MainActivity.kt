package sk.romanstrazanec.particleflowkotlin

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN

class MainActivity : AppCompatActivity() {
    private val gameCanvas = Canvas(this)
    private val updateHandler = @SuppressLint("HandlerLeak") object : Handler() {
        override fun handleMessage(msg: Message) {
            gameCanvas.update()
            gameCanvas.invalidate()
            super.handleMessage(msg)
        }
    }
    private val updateThread = UpdateThread(updateHandler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)

        setContentView(gameCanvas)
        updateThread.start()
    }
}
