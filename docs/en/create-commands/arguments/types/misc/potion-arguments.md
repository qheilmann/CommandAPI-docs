---
order: 11
authors:
  - DerEchtePilz
  - JorelAli
  - willkroboth
---

# Potion effect arguments

![An image of a potion argument with a list of potion effect suggestions](/images/arguments/potion.png)

The `PotionEffectArgument` class represents Minecraft potion effects. When used, this argument is casted to Bukkit's `PotionEffectType` class, or alternatively a `NamespacedKey` object if the `PotionEffectArgument.NamespacedKey` argument is used to create a `PotionEffectArgument`.

::::tip Example - Giving a player a potion effect

Say we wanted to have a command that gives a player a potion effect. For this command, we'll use the following syntax:

```mccmd
/potion <target> <potion> <duration> <strength>
```

In this example, we utilize some of the other arguments that we've described earlier, such as the `PlayerArgument` and `TimeArgument`. Since duration for the `PotionEffect` constructor is in ticks, this is perfectly fit for the `TimeArgument`, which is represented in ticks.

#### Use `PotionEffectArgument`

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/PotionArguments.java#potionEffectArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/PotionArguments.kt#potionEffectArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/PotionArguments.kt#potionEffectArgumentsExampleDSL
:::

#### Use `PotionEffectArgument.NamespacedKey`

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/PotionArguments.java#potionEffectArgumentsNamespacedKeyExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/PotionArguments.kt#potionEffectArgumentsNamespacedKeyExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/PotionArguments.kt#potionEffectArgumentsNamespacedKeyExampleDSL
:::

::::