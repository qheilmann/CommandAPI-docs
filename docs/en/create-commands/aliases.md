---
order: 8
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# Aliases

Aliases for commands can be added by using the `withAliases()` method when registering a command. Aliases allow you to run the same command with a different 'name' from the original registered command name.

::::tip Example – Using aliases for `/getpos`

In this example, we register the command `/getpos` that returns the command sender's location. We apply the aliases `/getposition`, `/getloc`, `/getlocation` and `/whereami` as well, using the `withAliases()` method.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/Aliases.java#aliasesExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/Aliases.kt#aliasesExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/Aliases.kt#aliasesExampleDSL
:::

::::