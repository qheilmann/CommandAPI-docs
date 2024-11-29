---
order: 4
authors: 
  - DerEchtePilz
  - JorelAli
  - MC-XiaoHei
---

# String arguments

There are three types of arguments that return Java's `String` object. Each has their own unique set of features which make them suitable for specific needs.

## String argument

The `StringArgument` class is used to represent a single word. These words **can only contain alphanumeric characters (A-Z, a-z and 0–9) and the underscore (_), plus (+), minus (-) and period (.) characters.**

Accepted `StringArgument` values:

```txt
Hello
123
hello123
hello-123
hello.WORLD
Hello_world
```

Rejected `StringArgument` values:

```txt
hello@email.com
yesn't
```

:::tip Examples of StringArgument uses

- Entering strings to identify offline players

:::

## Text argument

The `TextArgument` acts similar to any String in Java. These can be single words, like the `StringArgument`, or have additional characters (e.g., spaces, symbols) **if surrounded by quotes**. To type quotation marks, you can use `\"` (as similar to Java) to escape these special characters.

Accepted `TextArgument` values:

```txt
hello
"hello world!"
"hello@gmail.com"
"this has \" <<-- speech marks! "
```

Rejected `TextArgument` values:

```txt
hello world
私
"speech marks: ""
```

:::tip Examples of TextArgument uses

- Editing the contents of a sign
- A command that requires multiple text arguments (say, username and password?)

:::

## Greedy string argument

:::info

The `GreedyStringArgument`, similar to the `ChatArgument` uses the entire argument array from its current position. This means that it never ends, therefore if it is used, it must be the last element of your `List` of arguments.

For example, if you have a command `/message <message> <target>`, it would not be able to determine where the message ends and the `<target>` argument begins.

If a `GreedyStringArgument` or `ChatArgument` is not declared at the end of the `List` of arguments, or multiple of these arguments is used in the same `List`, the CommandAPI throws a `GreedyArgumentException`.

The `GreedyStringArgument` takes the `TextArgument` a step further. **Any characters and symbols are allowed** and quotation marks are not required.

:::

::::tip Example – Messaging command

Say we have a simple message command of the following form:

```mccmd
/message <target> <message>
```

This would be ideal for a greedy string, since it can consume all text after the player's name:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/StringsArguments.java#greedyStringArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/StringsArguments.kt#greedyStringArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/StringsArguments.kt#greedyStringArgumentsExampleDSL
:::

Any text entered after the `<target>` argument would be sent to the player. For example, the command could be used as follows:

```mccmd
/message Skepter This is some incredibly long string with "symbols" and $p3c!aL characters~
```

Note how this only works if the greedy string argument is _at the end_. If, say, the command was `/message <message> <target>`, it would not be able to determine where the `<message>` argument ends and the `<target>` argument begins.

::::

:::tip Examples of GreedyStringArgument uses

- A messaging/whisper command (as shown in the example above)
- A mailing command
- Any command involving lots of text, such as a command to write the contents of a book
- Any command that involves an unreasonable/unknown number of arguments
- Any command where you want to parse arguments similar to how regular Bukkit would

:::
