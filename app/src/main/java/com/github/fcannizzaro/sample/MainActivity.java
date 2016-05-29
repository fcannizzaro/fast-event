package com.github.fcannizzaro.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.fcannizzaro.fastevent.EventCallback;
import com.github.fcannizzaro.fastevent.FastEvent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView event = (TextView) findViewById(R.id.event_activity),
                eventService = (TextView) findViewById(R.id.event_service);

        FastEvent.enableLogs();

        multiOnClickistener(R.id.start_service, R.id.stop_operation, R.id.button);

        Intent backgroundService = new Intent(MainActivity.this, BackgroundService.class);
        startService(backgroundService);

        // define a custom event inside activity (called from fragment)

        FastEvent
                .on("in-activity")
                .onUi(this)
                .execute(new EventCallback() {
                    @Override
                    public void onEvent(Object... args) {

                        event.setText("in-activity-called! (" + args[0] + ")");

                    }
                });

        FastEvent
                .on("status")
                .onUi(this)
                .execute(new EventCallback() {
                    @Override
                    public void onEvent(Object... args) {
                        eventService.setText("service :  " + args[0]);
                    }
                });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SampleFragment())
                .commit();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start_service:
                FastEvent.emit("service", true);
                break;

            case R.id.stop_operation:
                FastEvent.emit("service", false);
                break;

            case R.id.button:
                FastEvent.emit("in-fragment", "ok");
                break;

        }

    }


    private void multiOnClickistener(int... ids) {
        for (int i : ids)
            findViewById(i).setOnClickListener(this);
    }
}
