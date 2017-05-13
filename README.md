# fast-event
Simple event creation

[![](https://jitpack.io/v/fcannizzaro/fast-event.svg)](https://jitpack.io/#fcannizzaro/fast-event)
[![Build Status](https://travis-ci.org/fcannizzaro/fast-event.svg?branch=master)](https://travis-ci.org/fcannizzaro/fast-event)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-fast--event-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3647)

# How to include

**Step 1.** Add it in your root **build.gradle** at the end of repositories:

```gradle
allprojects {
    repositories {
      ...
      maven { url "https://jitpack.io" }
    }
  }
```

**Step 2.** Add the dependency


```gradle
dependencies {
    compile 'com.github.fcannizzaro:fast-event:1.0.0'
}
```

# Static Methods

### bind(Class clazz, [, Activity activity])
register event annotations.

### emit(event, Object ... args)
emit event.

### delete(event)
delete event.

# Annotations

### @Event(String: event)
define method to run.

### @OnUi
run method on ui.

### @Async
run method on a thread.

# Usage

## Activity / Service / Class
```java
class Sample extends Activity {

    @OnUi
    @Event("my-event")
    private void onUpdate(String key, Integer counter) {
        // do something
    }

    @Async
    @Event("my-event-on-thread")
    private void onUpdate(String key) {
        // do something
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        FastEvent.bind(this);
        ...
    }
    
}
```

## Fragment
```java

class Sample extends Fragment {

    // define events
    // ...
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ...
        FastEvent.bind(this, getActivity());
        ...
    }
    
}
```

## Emit
```java
 FastEvent.emit("my-event", "fcannizzaro", 20);
```

# License
MIT - **Francesco Cannizzaro** 
