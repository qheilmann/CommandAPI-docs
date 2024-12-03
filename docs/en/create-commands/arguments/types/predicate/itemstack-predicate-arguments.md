---
order: 2
authors:
  - misode
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# ItemStack predicate arguments

Similar to the `BlockPredicateArgument`, the `ItemStackPredicateArgument` is a way of performing predicate checks on `ItemStack` objects. These can represent tags, such as the ones declared [here on the MinecraftWiki](https://minecraft.wiki/w/Tag#Items), or individual items. The cast type for this argument is `Predicate<ItemStack>`.

::::tip Example – Removing items in inventories based on predicates

Say we wanted to remove items in your inventory _(I know, the `/clear` command does this, but this is the only example I could come up with)_. To do this, we'll use the following command syntax:

```mccmd
/rem <item>
```

We implement this with a simple for loop over the player's inventory and remove items that satisfy the predicate.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/predicate/ItemStackPredicateArguments.java#itemStackPredicateArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/predicate/ItemStackPredicateArguments.kt#itemStackPredicateArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/predicate/ItemStackPredicateArguments.kt#itemStackPredicateArgumentsExampleDSL
:::

::::