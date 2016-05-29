package com.github.fcannizzaro.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.fcannizzaro.fastevent.EventCallback;
import com.github.fcannizzaro.fastevent.FastEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView event = (TextView) findViewById(R.id.event_activity);

        FastEvent.enableLogs();

        // define a custom event inside activity (called from fragment)

        FastEvent
                .on("in-activity")
                .onUi(this)
                .execute(new EventCallback() {
                    @Override
                    public void onEvent(Object... args) {

                        // do something

                        // cast your args

                        event.setText("in-activity-called! (" + args[0] + ")");

                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastEvent.emit("in-fragment", "ok");
            }
        });


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SampleFragment())
                .commit();

    }
}
