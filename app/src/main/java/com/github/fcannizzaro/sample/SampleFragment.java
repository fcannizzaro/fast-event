package com.github.fcannizzaro.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */
public class SampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        final Button button = (Button) v.findViewById(R.id.button1);
        final TextView event = (TextView) v.findViewById(R.id.event);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastEvent.emit("in-activity");
            }
        });

        // define a custom event inside fragment (called from activity)
        FastEvent
                .on("in-fragment")
                .onUi(getActivity())
                .execute(new Runnable() {
                    @Override
                    public void run() {

                        // do something

                        event.setText("in-fragment-called!");

                    }
                });

        return v;
    }
}

