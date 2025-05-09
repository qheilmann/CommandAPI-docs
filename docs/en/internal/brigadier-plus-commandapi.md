---
order: 2
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# Brigadier + CommandAPI

So far, we've been using only the CommandAPI to register commands. As a result, this makes the CommandAPI's features limited by whatever the CommandAPI has implemented. To push past these limits, the CommandAPI includes some extra methods to help with invoking brigadier methods. Of course, to use these methods, brigadier is required. The brigadier dependency's installation instructions can be found [here](https://github.com/Mojang/brigadier#installation).

:::info

For those that are unaware, [brigadier](https://github.com/Mojang/brigadier) is Mojang's command parser and dispatching framework. This is what the CommandAPI wraps around and is the main underlying source of its functionality.

:::

The CommandAPI has been designed in such a way that you shouldn't have to access NMS in oder to make use of the more "advanced" arguments and features - if you find that NMS is required to do something, [please make a new issue](https://github.com/CommandAPI/CommandAPI/issues/new/choose)!

## Brigadier support functions

The CommandAPI offers the following methods in the `dev.jorel.commandapi.Brigadier` class:

```java
public static CommandDispatcher getCommandDispatcher();
public static RootCommandNode getRootNode();
public static LiteralArgumentBuilder fromLiteralArgument(LiteralArgument literalArgument);
public static RedirectModifier fromPredicate(BiPredicate<CommandSender, Object[]> predicate, List<Argument> args);
public static Command fromCommand(CommandAPICommand command);
public static RequiredArgumentBuilder fromArgument(List<Argument> args, Argument<?> argument);
public static RequiredArgumentBuilder fromArgument(Argument argument);
public static SuggestionProvider toSuggestions(Argument<?> argument, List<Argument> args);
public static Object[] parseArguments(CommandContext cmdCtx, List<Argument> args);
public static Object getBrigadierSourceFromCommandSender(CommandSender sender);
public static CommandSender getBukkitCommandSenderFromContext(CommandContext cmdCtx);
```

Briefly, here's what each of these functions do (you can view the JavaDocs for more information):

| Method                                | Description                                                                                                                                |
|---------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `getCommandDispatcher`                | Returns the Minecraft command dispatcher graph                                                                                             |
| `getRootNode`                         | Returns the root node of the command dispatcher.<br>This is equivalent to using<br />`getCommandDispatcher().getRoot();`                   |
| `fromLiteralArgument`                 | Creates a `LiteralArgumentBuilder` from a `LiteralArgument`                                                                                |
| `fromPredicate`                       | Converts a predicate and some arguments into a `RedirectModifier`. This can be used for the `fork` method in brigadier's `ArgumentBuilder` |
| `fromCommand`                         | Converts a `CommandAPICommand` into a brigadier `Command` object                                                                           |
| `fromArgument`                        | Converts an argument, or a list of arguments, into a `RequiredArgumentBuilder`                                                             |
| `toSuggestions`                       | Converts an argument's suggestions into brigadier's `SuggestionProvider`, with a list of previously declared arguments                     |
| `parseArguments`                      | Parses a list of CommandAPI arguments into their respective objects for a provided `CommandContext`                                        |
| `getBrigadierSourceFromCommandSender` | Converts a Bukkit `CommandSender` into the NMS command sender source object                                                                |
| `getBukkitCommandSenderFromContext`   | Converts a Brigadier `CommandContext` into a Bukkit `CommandSender`                                                                        |

## Examples

I hope these examples help understand how the CommandAPI can help with registering more "powerful" commands with the use of brigadier as well! Please bear with it - these examples can be long, but I'm certain that they've been explained well and will be useful!

::::tip Example - Adding a predicate to the 'execute' command

Say we wanted to add a predicate to the `/execute` command. In this example, we'll create a predicate which handles random chances. To illustrate this, we want to be able to run commands such as:

```mccmd
/execute if randomchance 1 4 run say Hello!
```

In this scenario, if we ran this command, we would expect "Hello!" to appear in the chat with a $\frac{1}{4}$ chance. In particular, this is what we're trying to achieve:

- We want to create a predicate (true/false value) for the following syntax:

  ```mccmd
  randomchance <numerator> <denominator>
  ```

- We also want this predicate to come _after_ `execute if`:

```mermaid
graph TD
    A(execute) --> B(if)
    B --> C("randomchance &lt;numerator&gt; &lt;denominator&gt;")
```

- After entering our predicate, we want to route back to `execute` (because the argument after `execute` is `run`, which is used in our example command above):

```mermaid
graph TD
    A(execute) --> B(if)
    B --> C("randomchance &lt;numerator&gt; &lt;denominator&gt;")
    C --> D(execute)
```

#### Writing the code

Now that we've established what we want, we can finally begin writing the code! First we want to create a literal `randomchance`. It's a literal because literal values don't change (similar to say `run` or `if` from the `/execute` command). To create a literal, we'll use the `fromLiteralArgument` method described above, and then build it using the `.build()` method:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep1
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep1
:::

With that completed, we can now create our "argument" to this predicate. To do this, we'll use the regular declaration of arguments that we would normally use for commands. In this example, because we're computing $\frac{numerator}{denominator}$, we want our numerator to be 0 or greater and our denominator to be 1 or greater (we don't want any negative numbers or division by zero!):

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep2
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep2
:::

Now we're going to get into the very nitty-gritty part - the predicate declaration. First, we'll create some variables `numerator` and `denominator` to represent the brigadier instances of these arguments. This can be handled by using the `Brigadier.argBuildOf` function:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep3
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep3
:::

Now we'll define our predicate. Since this is sort of a "meta-command" (it directly affects the outcome of the `run` command), we need to use the `ArgumentBuilder`'s `fork` method. Remember that after we run this predicate, we want to link back to `execute` again, so our first argument is the `CommandNode` for `execute`, which we can get using `Brigadier.getRootNode().getChild("execute")`. Then, we can simply use `Brigadier.fromPredicate` to finish our declaration:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep4
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep4
:::

Finally, we can now link everything up. We know that `numerator` comes first, **then** `denominator`, so we have to have `numerator.then(denominator)`. We also know that these arguments are the **children** of the `randomChance` literal, so we use the following code to state all of this:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep5
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep5
:::

Finally, we "register" the command. In this case, we're actually just adding the `randomChance` node under `execute → if`, which we can add using the following code:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExampleStep6
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExampleStep6
:::

#### Code summary

So, hopefully that wasn't too confusing! If you're still lost, here's the whole code that we wrote:

:::tabs
===Java
<<< @/../reference-code/src/main/java/internal/BrigadierPlusCommandAPI.java#addPredicateExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/internal/BrigadierPlusCommandAPI.kt#addPredicateExample
:::

::::