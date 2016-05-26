package com.github.fcannizzaro.sample;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */
@SuppressWarnings("unused")
public class FastEvent {

    public final static String TAG = "FastEvent";

    private static boolean verbose;
    private static FastEvent instance = new FastEvent();
    private HashMap<String, Event> events;
    private ArrayList<String> disabled;

    private FastEvent() {
        events = new HashMap<>();
        disabled = new ArrayList<>();
    }

    /**
     * Enable logs on development
     */
    public static void enableLogs() {
        verbose = true;
    }

    /**
     * Log print
     */
    private static void log(String log) {
        if (verbose)
            Log.d(TAG, log);
    }

    /**
     * Create a new EventBuilder
     */
    public static EventBuilder on(String event) {
        return new EventBuilder(event, instance);
    }

    /**
     * Emit event
     */
    public static void emit(String event) {
        if (instance.events.containsKey(event) && !instance.disabled.contains(event)) {
            instance.events.get(event).run();
            log(event + " emitted");
        } else
            log(event + " not exist");
    }

    /**
     * Delete event
     */
    public static void delete(String event) {
        instance.events.remove(event);
        log(event + " deleted");
    }

    /**
         * Enable event (if disabled)
     */
    public static void enable(String event) {
        instance.disabled.remove(event);
        log(event + " enabled");
    }

    /**
     * Disable event (if enabled)
     */
    public static void disable(String event) {
        instance.disabled.add(event);
        log(event + " disabled");
    }

    /**
     * Register a new event
     */
    void register(Event event) {
        instance.events.put(event.getEvent(), event);
        log(event.getEvent() + " registered");
    }

}
