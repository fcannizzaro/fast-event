package com.github.fcannizzaro.sample;

import android.app.Activity;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */

@SuppressWarnings("unused")
public class Event {

    private Activity activity;
    private String event;
    private Runnable runnable;
    private boolean async;
    private boolean onUi;
    private int priority = Thread.NORM_PRIORITY;

    public void run() {

        if (async) {
            Thread thread = new Thread(runnable);
            thread.setPriority(priority);
            thread.start();
        } else if (onUi)
            activity.runOnUiThread(runnable);

        else
            runnable.run();

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

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


}
