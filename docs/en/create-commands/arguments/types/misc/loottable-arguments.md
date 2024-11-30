---
order: 7
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# LootTable argument

![A loot table argument showing a list of Minecraft loot tables as suggestions](/images/arguments/loottable.png)

The `LootTableArgument` class can be used to get a Bukkit `LootTable` object.

::::tip Example – Filling a chest with loot table contents

Say we wanted to write a command that populates a chest with some loot table contents. For this example, we'll use the following command:

```mccmd
/giveloottable <loottable> <location>
```

We ensure that the location provided is a container (such as a chest or shulkerbox) and then update the contents of that container:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/LootTableArguments.java#lootTableArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/LootTableArguments.kt#lootTableArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/LootTableArguments.kt#lootTableArgumentsExampleDSL
:::

::::