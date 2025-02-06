---
order: 2
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
  - Sytm
---

# Using the DSL

## Defining a simple message command

As a first example and to take a first look at the Kotlin DSL syntax, we will first create a simple command to send messages to a player.

::::tip Example – Sending a message to a player using the Kotlin DSL

We want to create a command that lets us send a message to a player. To do this, we want to register a command with the following syntax:

```mccmd
/sendmessageto <player> <msg>
```

We can then use the following command registration:

:::tabs key:dsl-usage-page
===CommandTree
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#dslTreeExample
===CommandAPICommand
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#dslExample
:::


Here you can see some interesting things:

- You do not need to call the `.register()` method when using the DSL
- You do not need to initialise any arguments

::::

## Executors

The Kotlin DSL also provides executors to execute your command. You've seen the `anyExecutor` in the example above.

To find out which DSL executor corresponds to "normal" executors, you can refer to the table below:

| DSL normal executor       | DSL resulting executor             | DSL normal execution info      | DSL resulting execution info            | "normal" Executor         |
|---------------------------|------------------------------------|--------------------------------|-----------------------------------------|---------------------------|
| `anyExecutor()`           | `anyResultingExecutor()`           | `anyExecutionInfo()`           | `anyResultingExecutionInfo`             | `executes()`              |
| `playerExecutor()`        | `playerResultingExecutor()`        | `playerExecutionInfo()`        | `playerResultingExecutionInfo()`        | `executesPlayer()`        |
| `entityExecutor()`        | `entityResultingExecutor()`        | `entityExecutionInfo()`        | `entityResultingExecutionInfo()`        | `executesEntity()`        |
| `consoleExecutor()`       | `consoleResultingExecutor()`       | `consoleExecutionInfo()`       | `consoleResultingExecutionInfo()`       | `executesConsole()`       |
| `commandBlockExecutor()`  | `commandBlockResultingExecutor()`  | `commandBlockExecutionInfo()`  | `commandBlockResultingExecutionInfo()`  | `executesCommandBlock()`  |
| `proxyExecutor()`         | `proxyResultingExecutor()`         | `proxyExecutionInfo()`         | `proxyResultingExecutionInfo()`         | `executesProxy()`         |
| `nativeExecutor()`        | `nativeResultingExecutor()`        | `nativeExecutionInfo()`        | `nativeResultingExecutionInfo()`        | `executesNative()`        |
| `remoteConsoleExecutor()` | `remoteConsoleResultingExecutor()` | `remoteConsoleExecutionInfo()` | `remoteConsoleResultingExecutionInfo()` | `executesRemoteConsole()` |

## Arguments

The DSL implements almost every argument with a method. You've seen the `playerArgument()` and the `greedyStringArgument()` method in the example at the top of this page.

The way arguments are implemented is pretty straight forward: It's basically the argument class' name, but as a method. So if you wanted to use a `ItemStackArgument` in your command, you would use the `itemStackArgument()` method of the DSL.

One thing to note is that the DSL also features every existing constructor. This means if you want to use an `IntegerArgument` with a minimum of `0` and a maximum of `10`, you normally would implement it like this:

```java
new IntegerArgument("integer", 0, 10)
```

However, when using this DSL it is implemented like this:

```kotlin
integerArgument("integer", 0, 10)
```

:::danger Developer's Note:

There are a few arguments not having a method which directly corresponds to their respective argument.

These arguments most likely use a builder pattern and because of that require further implementation by the user.

To use these arguments, the DSL also provides the `argument()` method which takes in any argument as a parameter.

:::

## Editing arguments

When using the DSL, you might want to modify the behaviour of certain arguments by adding requirements or suggestions to them.

To give you a general idea how you could accomplish that, the `sendMessageTo` command is adding a broadcast option which should only be executed by server operators.

:::tabs key:dsl-usage-page
===CommandTree
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#argumentRequirementsTreeExample
===CommandAPICommand
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#argumentRequirementsExample
:::

Notice how you can just add the requirement in a CommandTree by adding it to the argument block where you also define the next arguments and the executor.

However, when modifying the behaviour of an argument in a CommandAPICommand you have to add an extra block where you can implement the additional behaviour.

### Adding requirements to commands

Expanding on the previous example where we added a requirement to a single argument, we now also want to add a requirement to a whole command.

This works similar to how argument behaviour is modified in a CommandTree:

:::tabs key:dsl-usage-page
===CommandTree
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#commandRequirementsTreeExample
===CommandAPICommand
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#commandRequirementsExample
:::

## More examples

Now, a few more examples are shown to demonstrate the use of this DSL a little more:

::::tip Example – Implementing optional arguments with the Kotlin DSL

We want to create a `/give` command with the following syntax:

```mccmd
/optionalArgument give <item>
/optionalArgument give <item> <amount>
```

When using a CommandTree, you can do this by using the same executor in two places.

When using a CommandAPICommand, you can create an optional Argument by setting the `optional` value to `true`:

:::tabs key:dsl-usage-page
===CommandTree
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#optionalArgumentsTreeExample
===CommandAPICommand
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#optionalArgumentsExample
:::

::::

::::tip Example – Replacing suggestions using the Kotlin DSL

We want to create a command with the following syntax to demonstrate replacing suggestions using the Kotlin DSL:

```mccmd
/replaceSuggestions <strings>
```

Replacing suggestions works similar to how you would add a requirement to an argument as shown in [Editing arguments](#editing-arguments).

You just have to use the `replaceSuggestions` method this time:

:::tabs key:dsl-usage-page
===CommandTree
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#replaceSuggestionsTreeExample
===CommandAPICommand
<<< @/../reference-code/src/main/kotlin/kotlindsl/Usage.kt#replaceSuggestionsExample
:::

::::