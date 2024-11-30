---
order: 4
authors: 
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# BlockState arguments

![A block state argument with suggestions for Minecraft items](/images/arguments/blockstate.png)

The `BlockStateArgument` is used to represent data about blocks in the world. These refer to any blocks that have data or states, such as dispensers, signs, doors and pistons. The `BlockStateArgument` creates a Bukkit `BlockData` object when used.

:::info

Make sure to not confuse the cast type with `BlockState`. The naming of this argument refers to the internal Minecraft vanilla argument naming convention - **this argument casts to `BlockData` and NOT `BlockState`**.

:::

::::tip Example – Setting a block

Say we want a simple command to set the block that you're looking at. We'll use the following command syntax:

```mccmd
/set <block>
```

And then we can simply set our block using `setBlockData()`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/BlockStateArguments.java#blockStateArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/BlockStateArguments.kt#blockStateArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/BlockStateArguments.kt#blockStateArgumentsExampleDSL
:::

::::