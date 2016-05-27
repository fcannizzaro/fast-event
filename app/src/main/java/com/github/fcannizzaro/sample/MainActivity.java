package com.github.fcannizzaro.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.fcannizzaro.fastevent.FastEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView event = (TextView) findViewById(R.id.event_activity);

        FastEvent.enableLogs();

        FastEvent
                .on("in-activity")
                .onUi(this)
                .execute(new Runnable() {
                    @Override
                    public void run() {

                        // do something

                        event.setText("in-activity-called!");

                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastEvent.emit("in-fragment");
            }
        });


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SampleFragment())
                .commit();

    }
}
