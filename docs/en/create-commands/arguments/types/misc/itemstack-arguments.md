---
order: 6
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# ItemStack arguments

![An item stack argument with suggestions for Minecraft items](/images/arguments/itemstack.png)

The `ItemStackArgument` class represents in-game items. As expected, this should be cast to Bukkit's `ItemStack` object. The `ItemStack` which is returned by the `ItemStackArgument` always has a size of 1.

::::tip Example – Giving a player an itemstack

Say we want to create a command that gives you items. For this command, we will use the following syntax:

```mccmd
/item <itemstack>
```

With this syntax, we can easily create our command:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/ItemStackArguments.java#itemStackArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ItemStackArguments.kt#itemStackArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ItemStackArguments.kt#itemStackArgumentsExampleDSL
:::

::::