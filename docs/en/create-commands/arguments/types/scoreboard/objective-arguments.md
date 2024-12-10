---
order: 2
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# Objective arguments

In the CommandAPI, objectives are split into two classes:

- The `ObjectiveArgument` class, which represents objectives as a whole
- The `ObjectiveCriteriaArgument` class, which represents objective criteria

## Objective argument

The objective argument refers to a single scoreboard objective.

::::tip Example – Move objective to sidebar

As an example, let's create a command to move an objective to a player's sidebar. To do this, we will use the following command syntax:

```mccmd
/sidebar <objective>
```

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/scoreboard/ObjectiveArguments.java#objectiveArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/ObjectiveArguments.kt#objectiveArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/ObjectiveArguments.kt#objectiveArgumentsExampleDSL
:::

::::

## Objective criteria argument

The `ObjectiveCriteriaArgument` is fairly straight forward – it represents the criteria for an objective. Similar to Bukkit, the objective criteria is simply represented as a `String`, so it must be casted to a `String` when being used.

::::tip Example – Unregister all objectives by criteria

Say we wanted to create a command to unregister all objectives based on a given criteria. Let's create a command with the following form:

```mccmd
/unregisterall <objective critera>
```

To do this, we're going to take advantage of Bukkit's `Scoreboard.getObjectivesByCriteria(String)` method

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/scoreboard/ObjectiveArguments.java#objectiveCriteriaArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/ObjectiveArguments.kt#objectiveCriteriaArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/scoreboard/ObjectiveArguments.kt#objectiveCriteriaArgumentsExampleDSL
:::

::::