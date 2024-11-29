---
order: 3
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# Team arguments

The `TeamArgument` class interacts with the Minecraft scoreboard and represents a team.

::::tip Example - Toggling friendly fire in a team

Let's say we want to create a command to toggle the state of friendly fire in a team. We want a command of the following form

```mccmd
/togglepvp <team>
```

To do this, given a team we want to use the `setAllowFriendlyFire(boolean)` function.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/scoreboard/TeamArguments.java#teamArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/TeamArguments.kt#teamArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/TeamArguments.kt#teamArgumentsExampleDSL
:::

::::