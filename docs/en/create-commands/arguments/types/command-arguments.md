---
order: 15
authors:
  - DerEchtePilz
  - JorelAli
  - willkroboth
---

# Command arguments

![Command arguments](/images/commandargument.gif)

Command arguments allows users to provide an executable server command. The `CommandArgument` class lets you specify:

- Arbitrary commands - any command that the user has permissions to run can be provided.
- Restricted commands - only specific commands can be provided.

Using the `CommandArgument` will return a `CommandResult`, which contains a Bukkit `Command` instance representing the command to be executed, and a `String[]` of command arguments.

## Command results

The `CommandResult` record contains the following methods:

```java
public record CommandResult {
    Command command();
    String[] args();

    boolean execute(CommandSender target);
}
```

These methods can be used to retrieve information about the command that was provided by the user:

```java
Command command();
```

`command()` returns the Bukkit `Command` instance that the user provided. For example, if a player provided `/mycommand hello world`, then `command()` will represent the `/mycommand` command.

```java
String[] args();
```

`args()` returns an array of string argument inputs that were provided to the command. For example, if a player provided `/mycommand hello world`, then `args()` will be the following:

```java
[ "hello", "world" ]
```

```java
boolean execute(CommandSender target);
```

`execute(CommandSender)` runs the Bukkit `Command` using the arguments contained in the `CommandResult` as the given `CommandSender`. It returns true if the command dispatch succeeded, and false if it failed. Using this method is equivalent to running the following:

```java
result.command().execute(target, result.command().getLabel(), result.args());
```

## Arbitrary commands

Arbitrary commands let the user enter any command that they have permission to execute. To use arbitrary commands, you just need to use the `CommandArgument` normally.

::::tip Example - A `/sudo` command

We want to create a `/sudo` command which lets you execute a command as another online player.

![Sudo command example](/images/sudocommand.gif)

To do this, we want to use the following command syntax:

```mccmd
/sudo <target> <command>
```

In this example, we want to be able to run any arbitrary command, so we will simply use the `CommandArgument` on its own (without using suggestions). Using the `CommandArgument` generates a `CommandResult` and we can use the `.command()` and `.args()` methods above to access the command and arguments. We can make use of the `Command.execute()` method to execute our command and use the target player as the command sender.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/CommandArguments.java#sudoCommandExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/CommandArguments.kt#sudoCommandExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/CommandArguments.kt#sudoCommandExampleDSL
:::

::::

## Restricted commands

Restricted commands allows you to restrict what commands a user is allowed to submit in the `CommandArgument`. Commands can be restricted by replacing the `CommandArgument`'s suggestions using the `replaceSuggestions()` method. For better fine-tuning of what commands a user can submit, commands can also be restricted by using _suggestion branches_.

<!-- TODO: Give an example using .replaceSuggestions(). -->

::::tip Example – Restricting commands using suggestion branches

To demonstrate restricting commands, let's create a command argument that allows players to enter one of the following commands:

```mccmd
/tp <player> <target>
/give <player> <item> <amount>
```

Let's also add a restriction that the player can only use diamonds or dirt for the `/give` command, and they can only specify an amount if they selected dirt. Overall, our command argument should allow players to follow this path:

$$\begin{gather}
\texttt{(start)}\\\\
\swarrow\hspace{1.2cm}\searrow\\\\
\texttt{tp}\hspace{2cm}\texttt{give}\\\\
\swarrow\hspace{3cm}\searrow\\\\
\texttt{player}\hspace{3.25cm}\texttt{player}\\\\
\hspace{0.25cm}\swarrow\hspace{3.75cm}\swarrow\hspace{1cm}\searrow\\\\
\hspace{0.25cm}\texttt{target}\hspace{3cm}\texttt{diamond}\hspace{1.75cm}\texttt{dirt}\\\\
\hspace{4.9cm}\texttt{minecraft:diamond}\hspace{1.75cm}\texttt{minecraft:dirt}\\\\
\hspace{10cm}\downarrow\\\\
\hspace{10cm}\texttt{(amount)}\\\\
\end{gather}$$

In our diagram above, we have two main branches: `/tp` and `/give`. The `/tp` branch has `player` followed by `target`, and the `/give` branch has `player` and then that branches off into two new sections.

We can implement our `/tp` branch using the `SuggestionsBranch.suggest()` method, then provide argument suggestions for our options. In this case, we have `tp` and then a list of online players. We include the list of online players twice, because we need suggestions for `<player>` as well as `<target>`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/CommandArguments.java#suggestionBranchesStep1
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/CommandArguments.kt#suggestionBranchesStep1
:::

For the `/give` branch, we can use a similar thing, but we need to tell the CommandArgument that the `/give` command branches into "diamond" and "dirt" suggestions. We can do this by using the `.branch()` method to add a new nested list of suggestions:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/CommandArguments.java#suggestionBranchesStep2
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/CommandArguments.kt#suggestionBranchesStep2
:::

Adding everything together, we get this fully completed CommandArgument:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/CommandArguments.java#suggestionBranchesStep3
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/CommandArguments.kt#suggestionBranchesStep3
:::

::::

### Null and empty suggestions

In the above example about restricted commands, we used `null` and `ArgumentSuggestions.empty()` in our `SuggestionsBranch.suggest()` method. These special suggestions have specific effects when used in suggestions for the `CommandArgument`.

#### Null suggestions

Null suggestions ensure that the suggestions at the current position will not be overridden. In the case of the `CommandArgument`, this means that the default command suggestions will be provided. For example, if we have the following `null` entry in our suggestions, users are allowed to enter a value if they choose to do so, meaning that the examples below are all valid:

```java
SuggestionsBranch.suggest(
    ArgumentSuggestions.strings("give"),
    null,
    ArgumentSuggestions.empty()
)
```

```mccmd
/give dirt
/give diamond
/give apple
```

Ending the command argument with nothing is also equivalent to using `null`, for example the following suggestion branch allows any of the following commands:

```java
SuggestionsBranch.suggest(
    ArgumentSuggestions.strings("give"),
    ArgumentSuggestions.strings("dirt", "minecraft:dirt")
)
```

```mccmd
/give dirt
/give dirt 10
/give dirt 10 name:Hello
```

#### Empty suggestions

Empty suggestions that are provided using `ArgumentSuggestions.empty()` tell the `CommandArgument` to stop accepting further suggestions. This "ends" the command. Using the following example, this allows the user to enter `/give diamond` and only `/give diamond` - users cannot enter any other commands.

```java
SuggestionsBranch.suggest(
    ArgumentSuggestions.strings("give"),
    ArgumentSuggestions.strings("diamond", "minecraft:diamond"),
    ArgumentSuggestions.empty()
)
```

These commands are valid:

```mccmd
/give diamond
/give minecraft:diamond
```

These commands are not valid:

```mccmd
/give
/give diamond 10
```