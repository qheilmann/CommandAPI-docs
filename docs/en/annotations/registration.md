---
order: 3
authors:
  - JorelAli
---

# Registering annotation-based commands

Registering annotation-based commands is _really_ simple. To do this, we use the following method, where `className` is the name of a class with a `@Command` annotation:

```java
CommandAPI.registerCommand(className)
```

::::tip Example: Registering a Warp command

Say we have a simple command `/warp` that is defined as follows:

<<< @/../reference-code/src/main/java/annotations/Intro.java#annotationsExample

We can register this in our `onLoad()` method so we can use this command from within Minecraft functions:

<<< @/../reference-code/src/main/java/annotations/Registration.java#registerCommand

::::