---
order: 2
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
---

# Advancement arguments

![An advancement argument suggesting a list of Minecraft advancements](/images/arguments/advancement.png)

The `AdvancementArgument` class represents in-game advancements. As expected, the `AdvancementArgument` can be cast to Bukkit's `Advancement` class.

::::tip Example – Awarding a player an advancement

Say we want to award a player an advancement. First, we need the syntax that our command will use:

```mccmd
/award <player> <advancement>
```

Since we require a player, we will use the `PlayerArgument` for this example. Given a player, we can simply get the `AdvancementProgress` for that player, and then award the criteria required to fully complete the provided advancement.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/AdvancementArguments.java#advancementArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/AdvancementArguments.kt#advancementArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/AdvancementArguments.kt#advancementArgumentsExampleDSL
:::

::::