---
order: 3
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
---

# Proxied CommandSenders

The CommandAPI has extra support for vanilla Minecraft's `/execute` command, by allowing the CommandSender to be an instance of the `ProxiedCommandSender` class. This allows the CommandSender to contain two extra pieces of information: The "proxied sender" and the original sender.

> [!TIP] Example - Running a command as a chicken
> 
> Say we have a command which kills the sender of a command. This is easily implemented as follows:
> 
> :::tabs
> ===Java
> ```java
> // todo {{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:proxySender1}}
> ```
> ===Kotlin
> ```kotlin
> // todo {{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:proxySender1}}
> ```
> ===Kotlin DSL
> ```kotlin
> // todo {{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/ExamplesKotlinDSL.kt:proxySender1}}
> ```
> :::
> 
> But what if the sender of the command is _not_ a player? By using Minecraft's `/execute` command, we could execute the command as _any_ arbitrary entity, as shown with the command below:
> 
> ```mccmd
> /execute as @e[type=chicken] run killme
> ```
> 
> To handle this case, we can use the `.executesProxy()` method to ensure that the command sender is a `ProxiedCommandSender`. Then, we can kill the `callee` _(the entity which is being 'forced' to run the command `/killme`)_
> 
> :::tabs
> ===Java
> ```java
> // todo {{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:proxySender2}}
> ```
> ===Kotlin
> ```kotlin
> // todo {{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:proxySender2}}
> ```
> ===Kotlin DSL
> ```kotlin
> // todo {{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/ExamplesKotlinDSL.kt:proxySender2}}
> ```
> :::
> 
> This allows the command above to run successfully, killing all chickens it can find.
> 