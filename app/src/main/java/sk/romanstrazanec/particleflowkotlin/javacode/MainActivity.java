package sk.romanstrazanec.particleflowkotlin.javacode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

@SuppressLint("Registered")
class MainActivity extends AppCompatActivity {
    private GameCanvas gameCanvas;
    private Handler updateHandler;
    private UpdateThread updateThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        gameCanvas = new GameCanvas(this);
        createHandler();
        updateThread = new UpdateThread(updateHandler);

        setContentView(gameCanvas);
        if (updateThread != null)
            updateThread.start();
    }

    @SuppressLint("HandlerLeak")
    private void createHandler() {
        updateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (gameCanvas != null)
                    gameCanvas.invalidate();
                super.handleMessage(msg);
            }
        };
    }
}
