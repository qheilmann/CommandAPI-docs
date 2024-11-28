---
order: 3
authors:
- JorelAli
- misode
- DerEchtePilz
---

# Adventure chat arguments

:::info

The two following classes, `AdventureChatComponentArgument` and `AdventureChatArgument` depend on a Paper-based server which has the Adventure library. If you use this class on a server without the Adventure library, it will throw a `PaperAdventureNotFoundException`

:::

From Paper 1.16.5 build #473 onwards, Paper now includes [Kyori's Adventure API](https://github.com/KyoriPowered/adventure-platform). This library is a replacement of the BungeeCord chat API and has all the same functionality as the BungeeCord chat API (and more!). The documentation for this API can be found [here](https://docs.adventure.kyori.net/index.html).

Since this functions very similar to the Spigot chat arguments, this page won't reiterate everything about how it works, we'll just outline some examples of how to use these arguments instead.

## Adventure chat color argument

![Chatcolor argument in-game, displaying a list of Minecraft chat colors](/images/arguments/chatcolor.png)

The `AdventureChatColorArgument` class is used to represent a given chat color (e.g., red or green). This argument returns the `NamedTextColor` object. If `reset` is passed to this argument, this will return `NamedTextColor.WHITE`.

::::tip Example – Username color changing plugin

Say we want to create a plugin to change the color of a player's username. We want to create a command of the following form:

```mccmd
/namecolor <chatcolor>
```

We then use the `ChatColorArgument` to change the player's name color:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/chat/AdventureChatArguments.java#namedTextColorExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#namedTextColorExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#namedTextColorExampleDSL
:::

::::

## Adventure chat component argument

The `AdventureChatComponentArgument` class accepts raw chat-based JSON as valid input, as declared [here](https://minecraft.wiki/w/Raw_JSON_text_format). This is converted into Adventure's `Component` class.

::::tip Example – Opening a book with raw JSON content

In this example, we'll create a simple command which lets you show a book to a user. The syntax for our command is as follows:

```mccmd
/showbook <target> <title> <author> <contents>
```

We can construct a book using the Adventure API's `Book.book(Component, Component, Component...)` method. To convert our strings into `Component` objects, we use the `Component.text(String)` method. Since Paper supports the Adventure API natively, we can then send this book to a player using the `openBook(Book)` method:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/chat/AdventureChatArguments.java#componentExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#componentExample
===KotlinDSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#componentExampleDSL
:::

::::

## Adventure chat argument

The `AdventureChatArgument` class is the equivalent Adventure API class for the `ChatArgument` - it represents infinitely long strings similar to the `GreedyStringArgument` and allows entity selectors such as `@e`, `@p` and so on. The `AdventureChatArgument` returns a `Component`, similar to the `AdventureChatComponentArgument`.

::::tip Example – Sending personalized messages to players

We'll take the same example from the `ChatArgument` class, but using the `AdventureChatArgument` instead - We want to create a personalized message broadcasted to all users using a chat component that allows entity selectors. For this command, we want the following syntax:

```mccmd
/pbroadcast <message>
```

To broadcast an Adventure `Component` to all players on the server, we have to use Paper's `broadcast(Component, String)` method. This method requires a permission node which all players must have to receive the broadcasted message. By default, Bukkit-based servers (Spigot and Paper) use the `bukkit.broadcast.user` permission, which is described [here](https://bukkit.fandom.com/wiki/CraftBukkit_Commands#Additional_Permissions):

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/chat/AdventureChatArguments.java#chatArgumentExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#chatArgumentExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/chat/AdventureChatArguments.kt#chatArgumentExampleDSL

:::

::::