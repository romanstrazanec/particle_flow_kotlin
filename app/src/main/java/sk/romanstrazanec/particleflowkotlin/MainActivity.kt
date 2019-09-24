package sk.romanstrazanec.particleflowkotlin

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN

class MainActivity : AppCompatActivity() {
    private lateinit var gameCanvas: GameCanvas
    private lateinit var updateHandler: Handler
    private lateinit var updateThread: UpdateThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)

        gameCanvas = GameCanvas(this)
        createHandler()
        updateThread = UpdateThread(updateHandler)

        setContentView(gameCanvas)
        updateThread.start()
    }

    private fun createHandler() {
        updateHandler = @SuppressLint("HandlerLeak") object : Handler() {
            override fun handleMessage(msg: Message) {
                gameCanvas.invalidate()
                super.handleMessage(msg)
            }
        }
    }
}
