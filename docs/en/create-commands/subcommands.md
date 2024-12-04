---
order: 10
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# Subcommands

Subcommands is another method for registering commands that makes use of creating multiple different `CommandAPICommand` instances. Given a `CommandAPICommand`, we can add a subcommand by using the following method:

```java
CommandAPICommand withSubcommand(CommandAPICommand subcommand);
CommandAPICommand withSubcommands(CommandAPICommand... subcommands);
```

Using subcommands has no disadvantages to using regular commands with the `LiteralArgument` or `MultiLiteralArgument`, and should be slightly more intuitive to implement if you've used other command frameworks before.

::::tip Example – Permission system with subcommands

Say we wanted to write a permission management system. To do this, we'll use the following command structsyntaxure:

```mccmd
/perm group add <permission> <groupName>
/perm group remove <permission> <groupName>
/perm user add <permission> <userName>
/perm user remove <permission> <userName>
```

Let's start with the simplest example - the `/perm group ...` command. We have one command which is the following:

```mccmd
add <permission> <groupName>
```

We can implement this by creating a `CommandAPICommand` with the command name `add`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/Subcommands.java#subcommandsExampleStep1
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep1
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep1DSL
:::

Similarly, we have another part `remove <permission> <groupName>`. We can declare this similar to our `add` command. Once we've done that, we can now join everything up together. Here, we create a command `group` which adds the two other subcommands:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/Subcommands.java#subcommandsExampleStep2
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep2
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep2DSL
:::

Finally, we can link everything up together to the `perm` command and register the whole thing together:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/Subcommands.java#subcommandsExampleStep3
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep3
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsExampleStep3DSL
:::

Another, more intuitive method, is to shove everything in one go without creating lots of variables all over the place:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/Subcommands.java#subcommandsFullExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsFullExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/Subcommands.kt#subcommandsFullExampleDSL
:::

::::