---
order: 5
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# Enchantment arguments

![An enchantment argument suggesting a list of Minecraft enchantments](/images/arguments/enchantment.png)

The `EnchantmentArgument` class lets users input a specific enchantment. As you would expect, the cast type is Bukkit's `Enchantment` class.

::::tip Example – Giving a player an enchantment on their current item

Say we want to give a player an enchantment on the item that the player is currently holding. We will use the following command syntax:

```mccmd
/enchantitem <enchantment> <level>
```

Since most enchantment levels range between 1 and 5, we will also make use of the `IntegerArgument` to restrict the level of the enchantment by using its range constructor.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/EnchantmentArguments.java#enchantmentArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/EnchantmentArguments.kt#enchantmentArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/EnchantmentArguments.kt#enchantmentArgumentsExampleDSL
:::

::::