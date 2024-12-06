---
order: 1
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
  - Gregzeee
---

# Command arguments

Arguments in the CommandAPI are registered by using an `Argument[]` or `List<Argument>` object. There are two things you need to keep in mind when creating arguments:

* The order which they will be used
* The type of each argument

By definition of a `List`, the order of the elements inserted into it are preserved, meaning the order you add arguments to the `List` will be the resulting order of which arguments are presented to the user when they run that command.

Adding arguments for registration is simple:

```java
// Create a List
List<Argument> arguments = new ArrayList<>();

// Add an argument with the node "target", which is a PlayerArgument
arguments.add(new PlayerArgument("target"));
```

The String value is the node that is registered into Minecraft's internal command graph. This name is also used as a prompt that is shown to a player when they are entering the command.

The CommandAPI is very flexible when it comes to registering arguments, and lets you use a number of different methods to suit your preference:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/Arguments.java#registerArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsExampleDSL
:::

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/Arguments.java#registerArgumentsVarargExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsVarargExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsVarargExampleDSL
:::

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/Arguments.java#registerArgumentsListExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsListExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#registerArgumentsListExampleDSL
:::

## Argument Casting

To access arguments, they have to be casted to the type that the argument represents. The order of the arguments in the [`CommandArguments args`](./command-arguments) is the same as the order in which the arguments were declared.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/Arguments.java#argumentCastExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#argumentCastExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/Arguments.kt#argumentCastExampleDSL
:::

The type to cast each argument (declared in the `dev.jorel.commandapi.arguments` package) is listed below:

|                                                                                              Argument class | Data type                                                                                                                                                                                                                                           |
|------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|                                                             [`AngleArgument`](./types/misc/angle-arguments) | `float`                                                                                                                                                                                                                                             |
|                                                 [`AdvancementArgument`](./types/misc/advancement-arguments) | `org.bukkit.advancement.Advancement`                                                                                                                                                                                                                |
|                                            [`AdventureChatArgument`](./types/chat/adventure-chat-arguments) | `net.kyori.adventure.text.Component`                                                                                                                                                                                                                |
|         [`AdventureChatColorArgument`](./types/chat/adventure-chat-arguments#adventure-chat-color-argument) | `net.kyori.adventure.text.format.NamedTextColor`                                                                                                                                                                                                    |
| [`AdventureChatComponentArgument`](./types/chat/adventure-chat-arguments#adventure-chat-component-argument) | `net.kyori.adventure.text.Component`                                                                                                                                                                                                                |
|                                                           [`AxisArgument`](./types/position/axis-arguments) | `java.util.EnumSet<org.bukkit.Axis>`                                                                                                                                                                                                                |
|                                                             [`BiomeArgument`](./types/misc/biome-arguments) | `org.bukkit.block.Biome`                                                                                                                                                                                                                            |
|                                               [`BiomeArgument.NamespacedKey`](./types/misc/biome-arguments) | `org.bukkit.NamespacedKey`                                                                                                                                                                                                                          |
|                                     [`BlockPredicateArgument`](./types/predicate/block-predicate-arguments) | `java.util.function.Predicate`<br />&emsp;`<org.bukkit.block.Block>`                                                                                                                                                                                |
|                                                   [`BlockStateArgument`](./types/misc/blockstate-arguments) | `org.bukkit.block.data.BlockData`                                                                                                                                                                                                                   |
|                                          [`BooleanArgument`](./types/primitive-arguments#boolean-arguments) | `boolean`                                                                                                                                                                                                                                           |
|                                          [`ChatArgument`](./types/chat/spigot-chat-arguments#chat-argument) | `net.md_5.bungee.api.chat.BaseComponent[]`                                                                                                                                                                                                          |
|                               [`ChatColorArgument`](./types/chat/spigot-chat-arguments#chat-color-argument) | `org.bukkit.ChatColor`                                                                                                                                                                                                                              |
|                       [`ChatComponentArgument`](./types/chat/spigot-chat-arguments#chat-component-argument) | `net.md_5.bungee.api.chat.BaseComponent[]`                                                                                                                                                                                                          |
|                                                              [`CommandArgument`](./types/command-arguments) | `dev.jorel.commandapi.wrappers.CommandResult`                                                                                                                                                                                                       |
|                                                          [`CustomArgument<T, B>`](./types/custom-arguments) | `T`                                                                                                                                                                                                                                                 |
|                                         [`DoubleArgument`](./types/primitive-arguments#numerical-arguments) | `double`                                                                                                                                                                                                                                            |
|                                                 [`EnchantmentArgument`](./types/misc/enchantment-arguments) | `org.bukkit.enchantments.Enchantment`                                                                                                                                                                                                               |
|                [`EntitySelectorArgument.ManyEntities`](./types/entities-arguments#entity-selector-argument) | `Collection<org.bukkit.entity.Entity>`                                                                                                                                                                                                              |
|                 [`EntitySelectorArgument.ManyPlayers`](./types/entities-arguments#entity-selector-argument) | `Collection<org.bukkit.entity.Player>`                                                                                                                                                                                                              |
|                   [`EntitySelectorArgument.OneEntity`](./types/entities-arguments#entity-selector-argument) | `org.bukkit.entity.Entity`                                                                                                                                                                                                                          |
|                   [`EntitySelectorArgument.OnePlayer`](./types/entities-arguments#entity-selector-argument) | `org.bukkit.entity.Player`                                                                                                                                                                                                                          |
|                                     [`EntityTypeArgument`](./types/entities-arguments#entity-type-argument) | `org.bukkit.entity.EntityType`                                                                                                                                                                                                                      |
|                                          [`FloatArgument`](./types/primitive-arguments#numerical-arguments) | `float`                                                                                                                                                                                                                                             |
|                         [`FloatRangeArgument`](./types/ranged-arguments#the-integerrange--floatrange-class) | `dev.jorel.commandapi.wrappers.FloatRange`                                                                                                                                                                                                          |
|                                              [`FunctionArgument`](../functions-and-tags/function-arguments) | `dev.jorel.commandapi.wrappers.FunctionWrapper[]`                                                                                                                                                                                                   |
|                                   [`GreedyStringArgument`](./types/string-arguments#greedy-string-argument) | `String`                                                                                                                                                                                                                                            |
|                                        [`IntegerArgument`](./types/primitive-arguments#numerical-arguments) | `int`                                                                                                                                                                                                                                               |
|                       [`IntegerRangeArgument`](./types/ranged-arguments#the-integerrange--floatrange-class) | `dev.jorel.commandapi.wrappers.IntegerRange`                                                                                                                                                                                                        |
|                                                     [`ItemStackArgument`](./types/misc/itemstack-arguments) | `org.bukkit.inventory.ItemStack`                                                                                                                                                                                                                    |
|                             [`ItemStackPredicateArgument`](./types/predicate/itemstack-predicate-arguments) | `java.util.function.Predicate`<br />&emsp;`<org.bukkit.inventory.ItemStack>`                                                                                                                                                                        |
|                                                                    [`ListArgument`](./types/list-arguments) | `java.util.Collection<T>`                                                                                                                                                                                                                           |
|                                                      [`LiteralArgument`](./types/literal/literal-arguments) | N/A                                                                                                                                                                                                                                                 |
|                                     [`Location2DArgument`](./types/position/location-arguments#2d-location) | `dev.jorel.commandapi.wrappers.Location2D`                                                                                                                                                                                                          |
|                                       [`LocationArgument`](./types/position/location-arguments#3d-location) | `org.bukkit.Location`                                                                                                                                                                                                                               |
|                                           [`LongArgument`](./types/primitive-arguments#numerical-arguments) | `long`                                                                                                                                                                                                                                              |
|                                                     [`LootTableArgument`](./types/misc/loottable-arguments) | `org.bukkit.loot.LootTable`                                                                                                                                                                                                                         |
|                                                                      [`MapArgument`](./types/map-arguments) | `java.util.LinkedHashMap`                                                                                                                                                                                                                           |
|                                             [`MathOperationArgument`](./types/misc/mathoperation-arguments) | `dev.jorel.commandapi.wrappers.MathOperation`                                                                                                                                                                                                       |
|                                            [`MultiLiteralArgument`](./types/literal/multiliteral-arguments) | `String`                                                                                                                                                                                                                                            |
|                                             [`NamespacedKeyArgument`](./types/misc/namespacedkey-arguments) | `org.bukkit.NamespacedKey`                                                                                                                                                                                                                          |
|                                                           [`NBTCompoundArgument<T>`](./types/nbt-arguments) | The cast type changes depending on whether you're shading the CommandAPI or using the CommandAPI as a plugin:<br /><ul><li>Shading:<br />`T` (implemented yourself)</li><br /><li>Plugin:<br />`dev.jorel.commandapi.nbtapi.NBTContainer`</li></ul> |
|                            [`ObjectiveArgument`](./types/scoreboard/objective-arguments#objective-argument) | `org.bukkit.scoreboard.Objective`                                                                                                                                                                                                                   |
|           [`ObjectiveCriteriaArgument`](./types/scoreboard/objective-arguments#objective-criteria-argument) | `String`                                                                                                                                                                                                                                            |
|                                [`OfflinePlayerArgument`](./types/entities-arguments#offlineplayer-argument) | `org.bukkit.OfflinePlayer`                                                                                                                                                                                                                          |
|                                                       [`ParticleArgument`](./types/misc/particle-arguments) | `dev.jorel.commandapi.wrappers.ParticleData`                                                                                                                                                                                                        |
|                                              [`PlayerArgument`](./types/entities-arguments#player-argument) | `org.bukkit.entity.Player`                                                                                                                                                                                                                          |
|                                                     [`PotionEffectArgument`](./types/misc/potion-arguments) | `org.bukkit.potion.PotionEffectType`                                                                                                                                                                                                                |
|                                       [`PotionEffectArgument.NamespacedKey`](./types/misc/potion-arguments) | `org.bukkit.NamespacedKey`                                                                                                                                                                                                                          |
|                                                           [`RecipeArgument`](./types/misc/recipe-arguments) | The cast type changes depending on your Minecraft version:<br><ul><li>Version 1.14.4 and below:<br />`org.bukkit.inventory.Recipe`</li><br /><li>1.15 and above:<br />`org.bukkit.inventory.ComplexRecipe` </li></ul>                               |
|                                                   [`RotationArgument`](./types/position/rotation-arguments) | `dev.jorel.commandapi.wrappers.Rotation`                                                                                                                                                                                                            |
|                [`ScoreboardSlotArgument`](./types/scoreboard/scoreboard-arguments#scoreboard-slot-argument) | `dev.jorel.commandapi.wrappers.ScoreboardSlot`                                                                                                                                                                                                      |
|               [`ScoreHolderArgument.Single`](./types/scoreboard/scoreboard-arguments#score-holder-argument) | `String`                                                                                                                                                                                                                                            |
|             [`ScoreHolderArgument.Multiple`](./types/scoreboard/scoreboard-arguments#score-holder-argument) | `Collection<String>`                                                                                                                                                                                                                                |
|                                                             [`SoundArgument`](./types/misc/sound-arguments) | `org.bukkit.Sound`                                                                                                                                                                                                                                  |
|                                               [`SoundArgument.NamespacedKey`](./types/misc/sound-arguments) | `org.bukkit.NamespacedKey`                                                                                                                                                                                                                          |
|                                                [`StringArgument`](./types/string-arguments#string-argument) | `String`                                                                                                                                                                                                                                            |
|                                                         [`TeamArgument`](./types/scoreboard/team-arguments) | `org.bukkit.scoreboard.Team`                                                                                                                                                                                                                        |
|                                                    [`TextArgument`](./types/string-arguments#text-argument) | `String`                                                                                                                                                                                                                                            |
|                                                               [`TimeArgument`](./types/misc/time-arguments) | `int`                                                                                                                                                                                                                                               |
|                                                               [`UUIDArgument`](./types/misc/uuid-arguments) | `java.util.UUID`                                                                                                                                                                                                                                    |
|                                                             [`WorldArgument`](./types/misc/world-arguments) | `org.bukkit.World`                                                                                                                                                                                                                                  |