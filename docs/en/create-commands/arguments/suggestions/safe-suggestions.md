---
order: 4
authors:
  - Strokkur424
  - DerEchtePilz
  - willkroboth
  - JorelAli
---

# Type-Safe suggestions

So far, we've covered how to replace suggestions using the `replaceSuggestions()` method. The issue with using strings for suggestion listings is that they’re prone to errors - it is possible to suggest something which is not actually a valid argument, which makes that suggestion unusable. As a result, some arguments include the `replaceSafeSuggestions()`, which provides type-safety checks for argument suggestions, as well as automatic "Bukkit-to-suggestion" conversion.

The whole point of the safe argument suggestions method is that parameters entered in this method are **guaranteed** to work.

The use of the safe replace suggestions function is the same as `replaceSuggestions()` from the previous section, except instead of returning a `String[]`, you now return a `T[]`, where `T` is the class corresponding to the argument. This is described in more detail in the table below.

```java
Argument replaceSafeSuggestions(SafeSuggestions<T> suggestions);
Argument includeSafeSuggestions(SafeSuggestions<T> suggestions);
```

## The `SafeSuggestions` interface

Similar to the [`ArgumentSuggestions` interface](./suggestions#the-argumentsuggestions-interface), safe suggestions use the `SafeSuggestions` interface which is a functional interface that takes in a mapping function from an Object to a String and returns some `ArgumentSuggestions` which represent the argument's suggestions. Again, this is typically implemented for anyone that wants to use a more powerful suggestion system.

As with `ArgumentSuggestions`, the CommandAPI provides some methods to generate safe suggestions:

```java
SafeSuggestions<T> suggest(T... suggestions);
SafeSuggestions<T> suggest(Function<SuggestionInfo, T[]> suggestions);
SafeSuggestions<T> suggestAsync(Function<SuggestionInfo, CompletableFuture<T[]>> suggestions);

SafeSuggestions<T> tooltips(Tooltip<T>... suggestions);
SafeSuggestions<T> tooltips(Function<SuggestionInfo, Tooltip<T>[]> suggestions);
SafeSuggestions<T> tooltipsAsync(Function<SuggestionInfo, CompletableFuture<Tooltip<T>[]>> suggestions);
```

## Supported arguments

Not all arguments support safe suggestions. This is mostly due to implementation constraints or inadequate support by the Bukkit API.

The list of supported arguments are displayed in the following table. The parameter `T` (shown in the method signatures above) are also provided for each argument. This parameter is the same as the cast argument described in [Argument Casting](../command-arguments#argument-casting), except for a few exceptions which are outlined in **bold**.

|                                                                                      Argument | Class (T)                                      |
|----------------------------------------------------------------------------------------------:|:-----------------------------------------------|
|                                  [`AdvancementArgument`](../types/misc/advancement-arguments) | `org.bukkit.advancement.Advancement`           |
|                                            [`AxisArgument`](../types/position/axis-arguments) | `java.util.EnumSet<org.bukkit.Axis>`           |
|                                              [`BiomeArgument`](../types/misc/biome-arguments) | `org.bukkit.block.Biome`                       |
|                                             [`BooleanArgument`](../types/primitive-arguments) | **`Boolean`**                                  |
|                [`ChatColorArgument`](../types/chat/spigot-chat-arguments#chat-color-argument) | `org.bukkit.ChatColor`                         |
|                          [`DoubleArgument`](../types/primitive-arguments#numerical-arguments) | **`Double`**                                   |
|                                  [`EnchantmentArgument`](../types/misc/enchantment-arguments) | `org.bukkit.enchantments.Enchantment`          |
|                      [`EntityTypeArgument`](../types/entities-arguments#entity-type-argument) | `org.bukkit.entity.EntityType`                 |
|                           [`FloatArgument`](../types/primitive-arguments#numerical-arguments) | **`Float`**                                    |
|          [`FloatRangeArgument`](../types/ranged-arguments#the-integerrange--floatrange-class) | `dev.jorel.commandapi.wrappers.FloatRange`     |
|                             [`FunctionArgument`](../../functions-and-tags/function-arguments) | **`org.bukkit.NamespacedKey`**                 |
|                    [`GreedyStringArgument`](../types/string-arguments#greedy-string-argument) | `String`                                       |
|                         [`IntegerArgument`](../types/primitive-arguments#numerical-arguments) | **`Integer`**                                  |
|        [`IntegerRangeArgument`](../types/ranged-arguments#the-integerrange--floatrange-class) | `dev.jorel.commandapi.wrappers.IntegerRange`   |
|                                      [`ItemStackArgument`](../types/misc/itemstack-arguments) | `org.bukkit.inventory.ItemStack`               |
|                      [`Location2DArgument`](../types/position/location-arguments#2d-location) | `dev.jorel.commandapi.wrappers.Location2D`     |
|                        [`LocationArgument`](../types/position/location-arguments#3d-location) | `org.bukkit.Location`                          |
|                            [`LongArgument`](../types/primitive-arguments#numerical-arguments) | **`Long`**                                     |
|                                      [`LootTableArgument`](../types/misc/loottable-arguments) | `org.bukkit.loot.LootTable`                    |
|                              [`MathOperationArgument`](../types/misc/mathoperation-arguments) | `dev.jorel.commandapi.wrappers.MathOperation`  |
|                                               [`NBTCompoundArgument`](../types/nbt-arguments) | `de.tr7zw.nbtapi.NBTContainer`                 |
|             [`ObjectiveArgument`](../types/scoreboard/objective-arguments#objective-argument) | **`org.bukkit.scoreboard.Objective`**          |
|                        [`OfflinePlayerArgument`](../types/entities-arguments#player-argument) | `org.bukkit.OfflinePlayer`                     |
|                                        [`ParticleArgument`](../types/misc/particle-arguments) | `org.bukkit.Particle`                          |
|                               [`PlayerArgument`](../types/entities-arguments#player-argument) | `org.bukkit.entity.Player`                     |
|                                      [`PotionEffectArgument`](../types/misc/potion-arguments) | `org.bukkit.potion.PotionEffectType`           |
|                                            [`RecipeArgument`](../types/misc/recipe-arguments) | `org.bukkit.inventory.Recipe`                  |
|                                    [`RotationArgument`](../types/position/rotation-arguments) | `dev.jorel.commandapi.wrappers.Rotation`       |
| [`ScoreboardSlotArgument`](../types/scoreboard/scoreboard-arguments#scoreboard-slot-argument) | `dev.jorel.commandapi.wrappers.ScoreboardSlot` |
|                                              [`SoundArgument`](../types/misc/sound-arguments) | `org.bukkit.Sound`                             |
|                                          [`TeamArgument`](../types/scoreboard/team-arguments) | **`org.bukkit.scoreboard.Team`**               |
|                                                [`TimeArgument`](../types/misc/time-arguments) | **`dev.jorel.commandapi.wrappers.Time`**       |
|                                              [`WorldArgument`](../types/misc/world-arguments) | `org.bukkit.World`                             |

## Safe time arguments

While most of the arguments are fairly straight forward, I'd like to bring your attention to the `TimeArgument`'s safe suggestions function. This uses `dev.jorel.commandapi.wrappers.Time` as the class for `T` to ensure type-safety. The `Time` class has three static methods:

```java
Time ticks(int ticks);
Time days(int days);
Time seconds(int seconds);
```

These create representations of ticks (e.g. `40t`), days (e.g. `2d`) and seconds (e.g. `60s`) respectively.

## Safe function arguments

Although all safe arguments are indeed "type-safe", the function argument uses a `NamespacedKey` which cannot be checked fully at compile time. As a result, this is argument should be used with caution - providing a `NamespacedKey` suggestion that doesn’t exist when the server is running will cause that command to fail if that suggestion is used.

## Safe scoreboard slot arguments

Scoreboard slots now include two new static methods so they can be used with safe arguments:

```java
ScoreboardSlot of(DisplaySlot slot);
ScoreboardSlot ofTeamColor(ChatColor color);
```

This allows you to create `ScoreboardSlot` instances which can be used with the safe replace suggestions method.

## Examples

While this should be fairly straight forward, here's a few examples of how this can be used in practice:

::::tip Example - Safe recipe arguments

Say we have a plugin that registers custom items which can be crafted. In this example, we use an "emerald sword" with a custom crafting recipe. Now say that we want to have a command that gives the player the item from our declared recipes, which will have the following syntax:

```mccmd
/giverecipe <recipe>
```

To do this, we first register our custom items:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#registerCustomItem
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#registerCustomItem
:::

Once we've done that, we can now include them in our command registration. To do this, we use `replaceSafeSuggestions(recipes)` and then register our command as normal:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#registerCommandWithSafeSuggestions
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#registerCommandWithSafeSuggestions
:::

::::

::::tip Example - Safe `/spawnmob` suggestions

Say we have a command to spawn mobs:

```mccmd
/spawnmob <mob>
```

Now say that we don't want non-op players to spawn bosses. To do this, we'll create a `List<EntityType>` which is the list of all mobs that non-ops are allowed to spawn:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#createForbiddenMobsList
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#createForbiddenMobsList
:::

We then use our safe arguments to return an `EntityType[]` as the list of values that are suggested to the player. In this example, we use the `sender()` method to determine if the sender has permissions to view the suggestions:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#createSafeArguments
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#createSafeArguments
:::

Now we register our command as normal:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#registerSpawnMobCommand
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#registerSpawnMobCommand
:::

::::

::::tip Example – Removing a potion effect from a player

Say we wanted to remove a potion effect from a player. To do this, we'll use the following command syntax:

```mccmd
/removeeffect <player> <potioneffect>
```

Now, we don't want to remove a potion effect that doesn't exist on a player, so instead we'll use the safe arguments to find a list of potion effects on the target player and then only suggest those potion effects. To do this, we'll use the `previousArguments()` method, as it allows us to access the previously defined `<player>` argument.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#createSafePotionEffectArguments
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#createSafePotionEffectArguments
:::

And then we can register our command as normal:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/suggestions/SafeSuggestions.java#registerRemoveEffectCommand
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/suggestions/SafeSuggestions.kt#registerRemoveEffectCommand
:::

::::