---
order: 16
authors:
  - DerEchtePilz
  - JorelAli
  - willkroboth
---

# World arguments

![A picture of world arguments in action](/images/arguments/worldargument.png)

The `WorldArgument` class allows a command sender to refer to a loaded Bukkit `World`.

::::tip Example – Unloading world

Say we want to unload a world on our Minecraft server. We want to create a command with the following syntax:

```mccmd
/unloadworld <world>
```

Using the world from the `WorldArgument`, we can then unload the world safely using `Bukkit.getServer().unloadWorld()` and passing `true` (to save chunks):

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/WorldArguments.java#worldArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/WorldArguments.kt#worldArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/WorldArguments.kt#worldArgumentsExampleDSL
:::

::::