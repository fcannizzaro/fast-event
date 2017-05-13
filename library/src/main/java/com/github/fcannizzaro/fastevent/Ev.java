package com.github.fcannizzaro.fastevent;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */

@SuppressWarnings("unused")
public class Ev {

    private Object instance;
    private Method method;

    private Activity activity;
    private String event;
    private boolean async;
    private boolean onUi;

    public Ev(String event, Object instance, Method method, Activity activity) {
        this.event = event;
        this.instance = instance;
        this.method = method;
        this.activity = activity;
    }

    void run(final Object... args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    method.invoke(instance, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        try {

            System.out.println(onUi + " " + activity);

            if (async) {
                Thread thread = new Thread(runnable);
                thread.start();
            } else if (onUi) {
                activity.runOnUiThread(runnable);
            } else {
                runnable.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String getEvent() {
        return event;
    }

    void setOnUi(boolean onUi) {
        this.onUi = onUi;
    }

    void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public String toString() {
        return event + " " + activity;
    }

}
