# fast-event
Simple event creation

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
    compile 'com.github.fcannizzaro:fast-event:0.1.0'
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

# License
MIT - Francesco Cannizzaro 