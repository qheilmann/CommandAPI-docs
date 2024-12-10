---
order: 3
authors:
  - willkroboth
---

# Loading The CommandAPI in Tests

## Plugin Dependency

If your plugin depends on the CommandAPI plugin to load, when running tests you should load the `JavaPlugin` `MockCommandAPIPlugin` before you use MockBukkit to [load your plugin](https://mockbukkit.readthedocs.io/en/latest/first_tests.html#creating-the-test-class). You can either load this class directly with `MockBukkit.load(MockCommandAPIPlugin.class)`, or use one of the static `MockCommandAPIPlugin#load` methods:

```java
MockCommandAPIPlugin load()
```

Load the CommandAPI Plugin in the test environment. Works exactly the same as `MockBukkit.load(MockCommandAPIPlugin.class)`.

```java
MockCommandAPIPlugin load(Consumer<CommandAPIBukkitConfig> configureSettings)
```

Load the CommandAPI Plugin after applying the given consumer. This allows configuring any setting from the [config.yml](../user-setup/config#configuration-settings) using the methods provided by [CommandAPIBukkitConfig](../dev-setup/shading#loading).

:::tip Example - Loading test CommandAPI with settings

To change, for example, the `missing-executor-implementation` message while running tests, you can use the method `CommandAPIBukkitConfig#missingExecutorImplementationMessage` when the `configureSettings` callback is run:

<<< @/../reference-code/src/test/java/test/LoadMockCommandAPI.java#loadMockCommandAPIExample

:::

## Shaded Dependency

If your plugin shades the CommandAPI, the CommandAPI will automatically load as usual when you use MockBukkit to load your plugin. Just note that you **must** call `CommandAPI.onDisable()` in your plugin's `onDisable` method in order for the test environment to reset properly after each test.

## Loading a custom CommandAPI platform implementation

By default, the testing environment will load `MockCommandAPIBukkit` as the CommandAPI platform object. This works for basic tests, but many methods in `MockCommandAPIBukkit` are not yet implemented and just throw an `UnimplementedMethodException`. This may cause your tests to fail if your code relies on any of these methods. If you see an `UnimplementedMethodException`, please tell us about it with a [GitHub Issue](https://github.com/CommandAPI/CommandAPI/issues) or a message in the CommandAPI Discord so we can get it solved for everyone.

In the short term, you can also try to avoid an `UnimplementedMethodException` by implementing the required method yourself. Simply create a class that extends `MockCommandAPIBukkit` and override the required method with an appropriate implementation. Before each test where you want to use your custom implementation, make sure to call `CommandAPIVersionHandler#usePlatformImplementation` to let the CommandAPI know what it should load.

<<< @/../reference-code/src/test/java/test/LoadMockCommandAPI.java#loadCustomCommandAPIPlatformImplementationExample