package com.github.fcannizzaro.fastevent;

import android.app.Activity;
import android.util.Log;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */

@SuppressWarnings("unused")
public class Event {

    private Activity activity;
    private String event;
    private EventCallback callback;
    private boolean async;
    private boolean onUi;
    private int priority = Thread.NORM_PRIORITY;

    void run(final Object... args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onEvent(new Args(args));
            }
        };

        try {

            if (async) {
                Thread thread = new Thread(runnable);
                thread.setPriority(priority);
                thread.start();
            } else if (onUi)
                activity.runOnUiThread(runnable);

            else
                runnable.run();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(FastEvent.TAG, "event " + event + " failed");
        }

    }

    // getters

    String getEvent() {
        return event;
    }

    // setters

    void setPriority(int priority) {
        this.priority = priority;
    }

    void setOnUi(boolean onUi) {
        this.onUi = onUi;
    }

    void setAsync(boolean async) {
        this.async = async;
    }

    void setCallback(EventCallback callback) {
        this.callback = callback;
    }

    void setEvent(String event) {
        this.event = event;
    }

    void setActivity(Activity activity) {
        this.activity = activity;
    }


}
