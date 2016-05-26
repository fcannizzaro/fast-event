# fast-event
Simple event creation

# Static Methods

## enableLogs()
enable verbose log (development)

## on(event)
create a new EventBuilder

## emit(event)
emit event

## delete(event)
delete event

## enable(event)
enable event (if disabled)

## disable(event)
disable event (if enabled)

# EventBuilder Methods

## async()
run event runnable inside a different thread

## maxPriority()
use max priority in thread execution

## minPriority()
use min priority in thread execution

## onUi()
run runnable inside Main Thread UI

## execute(Runnable)
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