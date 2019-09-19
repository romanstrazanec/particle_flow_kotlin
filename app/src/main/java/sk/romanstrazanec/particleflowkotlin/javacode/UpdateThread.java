package sk.romanstrazanec.particleflowkotlin.javacode;

import android.os.Handler;

class UpdateThread extends Thread {

    private final Handler updatingHandler;

    UpdateThread(Handler updatingHandler) {
        this.updatingHandler = updatingHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(50);
            } catch (Exception ignored) {
            }
            if (updatingHandler != null) updatingHandler.sendEmptyMessage(0);
        }
    }
}
