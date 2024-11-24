---
order: 3
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
---

# Listed arguments

Arguments have a setting which determine whether they are present in the [`CommandArguments args`](./arguments) that is populated when executing a command.

By default, the `LiteralArgument` has this setting set to `false`, hence the literal values are _not_ present in the [`CommandArguments args`](arguments).

This flag is set using the following function:

```java
Argument setListed(boolean listed);
```

> [!TIP] Example - Setting listed arguments
> 
> Say we have the following command:
> 
> ```mccmd
> /mycommand <player> <value> <message>
> ```
> 
> Let's also say that in our implementation of this command, we don't actually perform any processing for `<value>`. Hence, listing it in the [`CommandArguments args`](./arguments) is unnecessary.
> 
> :::tabs
> ===Java
> ```java
> // todo {{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:listed1}}
> ```
> ===Kotlin
> ```kotlin
> {{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:listed1}}
> ```
> :::
> 
> In this scenario, the argument `<value>` is not present in the [`CommandArguments args`](./arguments) for the executor.
> 