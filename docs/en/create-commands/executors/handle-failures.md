---
order: 6
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
  - Xemii16
---

# Handle command failures

Sometimes, you want your command to fail on purpose. This is the way to "gracefully" handle errors in your command execution. This is performed by throwing any of the following methods:

```java
throw CommandAPI.failWithString(String message);
throw CommandAPI.failWithMessage(Message message);
throw CommandAPIBukkit.failWithBaseComponents(BaseComponent... message);
throw CommandAPIBukkit.failWithAdventureComponent(Component message);
throw CommandAPIBukkit.failWithAdventureComponent(ComponentLike message);
```

When the CommandAPI handles the fail method, it will cause the command to return a _success value_ of 0, to indicate failure.

::::tip Example - Command failing for an element not in a list

Say we have some list containing fruit and the player can choose from it. To do that, we can use a `StringArgument` and suggest it to the player using `.replaceSuggestions(info -> String[])`. However, because this only lists _suggestions_ to the player, it does **not** stop the player from entering an option that isn't on the list of suggestions.

Therefore, to gracefully handle this with a proper error message, we use one of the `CommandAPI.failWithXXX()` methods above with a meaningful error message which is displayed to the user.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/executors/HandleFailures.java#handleFailuresExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/executors/HandleFailures.kt#handleFailuresExample
:::

::::

:::info
In general, it's a good idea to handle unexpected cases with one of the `CommandAPI.failWithXXX()` methods.
Most arguments used by the CommandAPI will have their own built-in failsafe system _(e.g., the `EntitySelectorArgument` will not execute the command executor if it fails to find an entity)_, so this feature is for those extra cases.
:::