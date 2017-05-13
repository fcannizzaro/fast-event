package com.github.fcannizzaro.sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.fcannizzaro.fastevent.FastEvent;
import com.github.fcannizzaro.fastevent.annotations.Event;

import java.text.DateFormat;
import java.util.Date;

public class BackgroundService extends Service {

    private boolean enabled;

    @Event("service")
    public void update(boolean status) {

        if (status != enabled) {
            FastEvent.emit("status", status ? "starting" : "off");
        }

        enabled = status;

    }

    @Override
    public void onCreate() {

        super.onCreate();
        FastEvent.bind(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    while (enabled) {

                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        FastEvent.emit("status", "working -> " + currentDateTimeString);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }

        }).start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
