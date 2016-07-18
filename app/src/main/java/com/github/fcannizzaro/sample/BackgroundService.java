package com.github.fcannizzaro.sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.fcannizzaro.fastevent.Args;
import com.github.fcannizzaro.fastevent.EventCallback;
import com.github.fcannizzaro.fastevent.FastEvent;

import java.text.DateFormat;
import java.util.Date;

public class BackgroundService extends Service {

    private boolean enabled;

    @Override
    public void onCreate() {

        super.onCreate();

        FastEvent
                .on("service")
                .execute(new EventCallback() {
                    @Override
                    public void onEvent(Args args) {

                        boolean status = args.get(0);

                        if (status != enabled)
                            FastEvent.emit("status", status ? "starting" : "off");

                        enabled = status;

                    }
                });

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    while (enabled) {

                        System.out.println("do something");

                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());

                        FastEvent.emit("status", "working -> " + currentDateTimeString);

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ignored) {

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
