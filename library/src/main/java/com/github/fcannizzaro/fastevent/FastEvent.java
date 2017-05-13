package com.github.fcannizzaro.fastevent;

import android.app.Activity;

import com.github.fcannizzaro.fastevent.annotations.Async;
import com.github.fcannizzaro.fastevent.annotations.Event;
import com.github.fcannizzaro.fastevent.annotations.OnUi;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Francesco Cannizzaro (fcannizzaro)
 */
@SuppressWarnings("unused")
public class FastEvent {

    private HashMap<String, Ev> events;
    private static FastEvent instance = new FastEvent();

    private FastEvent() {
        events = new HashMap<>();
    }

    public static void bind(Object clazz) {
        bind(clazz, clazz instanceof Activity ? (Activity) clazz : null);
    }

    public static void bind(Object clazz, Activity activity) {
        for (Method method : clazz.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Event.class)) {
                instance.register(method, clazz, activity);
            }
        }
    }

    private void register(final Method method, final Object clazz, final Activity activity) {

        Event annotation = method.getAnnotation(Event.class);
        OnUi onUi = method.getAnnotation(OnUi.class);
        Async async = method.getAnnotation(Async.class);

        Ev event = new Ev(annotation.value(), clazz, method, activity);
        event.setAsync(async != null);
        event.setOnUi(onUi != null);
        method.setAccessible(true);

        instance.events.put(event.getEvent(), event);

    }

    /**
     * Emit event
     */
    public static void emit(String event, Object... args) {
        if (instance.events.containsKey(event)) {
            instance.events.get(event).run(args);
        }
    }

    /**
     * Delete event
     */
    public static void delete(String event) {
        instance.events.remove(event);
    }

}
