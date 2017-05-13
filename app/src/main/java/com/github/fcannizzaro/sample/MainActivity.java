package com.github.fcannizzaro.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.fcannizzaro.fastevent.FastEvent;
import com.github.fcannizzaro.fastevent.annotations.Event;
import com.github.fcannizzaro.fastevent.annotations.OnUi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView event, eventService;

    @OnUi
    @Event("in-activity")
    private void updateText(String text) {
        event.setText("in-activity-called! (" + text + ")");
    }

    @OnUi
    @Event("status")
    private void updateStatus(String status) {
        eventService.setText("service :  " + status);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FastEvent.bind(this);

        event = (TextView) findViewById(R.id.event_activity);
        eventService = (TextView) findViewById(R.id.event_service);

        multiOnClickistener(R.id.start_service, R.id.stop_operation, R.id.button);

        Intent backgroundService = new Intent(MainActivity.this, BackgroundService.class);
        startService(backgroundService);

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
