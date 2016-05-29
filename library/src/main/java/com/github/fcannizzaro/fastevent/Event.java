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


    public void run(final Object... args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onEvent(args);
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

    public String getEvent() {
        return event;
    }

    // setters

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setOnUi(boolean onUi) {
        this.onUi = onUi;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public void setCallback(EventCallback callback) {
        this.callback = callback;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


}
