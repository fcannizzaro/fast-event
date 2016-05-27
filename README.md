# fast-event
Simple event creation

[![](https://jitpack.io/v/fcannizzaro/fast-event.svg)](https://jitpack.io/#fcannizzaro/fast-event)

# Get It 

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
      ...
      maven { url "https://jitpack.io" }
    }
  }
```

Step 2. Add the dependency


```gradle
dependencies {
    compile 'com.github.fcannizzaro:fast-event:0.1.1'
}
```

# Static Methods

### enableLogs()
enable verbose log (development)

### on(event)
create a new EventBuilder

### emit(event)
emit event

### delete(event)
delete event

### enable(event)
enable event (if disabled)

### disable(event)
disable event (if enabled)

# EventBuilder Methods

### async()
run event runnable inside a different thread

### maxPriority()
use max priority in thread execution

### minPriority()
use min priority in thread execution

### onUi(Activity)
run runnable inside Main Thread UI

### execute(Runnable)
runnable to run on event

# Sample
```java

FastEvent
  .on("my-event")
  .onUi(this)
  .execute(new Runnable() {
      @Override
        public void run() {
  
            // do something
  
      }
  });

FastEvent
  .on("my-event-on-thread")
  .async()
  .maxPriority()
  .execute(runnable);
  
 FastEvent.emit("my-event");

```

# Sample (GIF)

![](https://github.com/fcannizzaro/fast-event/blob/master/sample.gif)

# License
MIT - Francesco Cannizzaro 
