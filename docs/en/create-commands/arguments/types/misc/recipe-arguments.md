---
order: 12
authors:
  - DerEchtePilz
  - JorelAli
  - willkroboth
---

# Recipe arguments

![A recipe argument command with the suggestions for Minecraft items](/images/arguments/recipe.png)

The `RecipeArgument` class lets you retrieve Bukkit's `ComplexRecipe` object.

::::tip Example - Giving a player the result of a recipe

Say we want to give yourself the result of a specific recipe. Since Bukkit's `Recipe` class contains the `getResult()` method, we will use that in our example. We want to create the following command:

```mccmd
/giverecipe <recipe>
```

As such, we easily implement it by specifying the `RecipeArgument`, casting it and adding it to the player's inventory:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/RecipeArguments.java#getResultExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/RecipeArguments.kt#getResultExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/RecipeArguments.kt#getResultExampleDSL
:::

::::

::::tip Example - Unlocking a recipe for a player

In this example, we'll use the `ComplexRecipe`'s `getKey()` method to write an example to to unlock a recipe for a player. For this command, we'll use the following syntax:

```mccmd
/unlockrecipe <player> <recipe>
```

This is then implemented trivially as follows:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/RecipeArguments.java#getKeyExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/RecipeArguments.kt#getKeyExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/RecipeArguments.kt#getKeyExampleDSL
:::

::::