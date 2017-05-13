package com.github.fcannizzaro.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.fcannizzaro.fastevent.FastEvent;
import com.github.fcannizzaro.fastevent.annotations.Event;
import com.github.fcannizzaro.fastevent.annotations.OnUi;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */
public class SampleFragment extends Fragment {

    private TextView event;

    @OnUi
    @Event("in-fragment")
    public void updateText(String ev) {
        event.setText("in-fragment-called! (" + ev + ")");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastEvent.bind(this, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        final Button button = (Button) v.findViewById(R.id.button1);
        event = (TextView) v.findViewById(R.id.event);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastEvent.emit("in-activity", "ok");
            }
        });

        return v;
    }
}

