---
order: 1
outline: 2
authors:
  - JorelAli
  - DerEchtePilz
---

# Introduction

Welcome to the documentation for the CommandAPI. The CommandAPI lets you create vanilla Minecraft commands which use the new command features which were implemented in Minecraft 1.13, including but not limited to:

- Having commands compatible with the vanilla `/execute` command
- Having commands which can be run using Minecraft functions
- Having better auto-completion and suggestions
- Having command type checks before execution (e.g., ensuring a number is within a certain range)

## How the CommandAPI works

::: info
This is a pretty important section, I would recommend reading before implementing the CommandAPI in your own projects.
This section tells you about things that aren’t stated anywhere else in the documentation.
Think of it as the "knowledge you should know before using this API".
:::

The CommandAPI does not follow the "standard" method of registering commands. In other words, commands which are registered with the CommandAPI will be registered as pure vanilla Minecraft commands as opposed to Bukkit or Spigot commands. This means that the following implications exist:

- **Commands should not be declared in the `plugin.yml` file.**
- Commands are automatically registered under the `minecraft` namespace. For example, if you register a command `/hello`, you can also run it using `/minecraft:hello`. However, you can change this default `minecraft` namespace. More about this [on the command registration page](./create-commands/registration#registering-the-command).
- Commands are not "linked" to a certain plugin. In other words, you can’t look up which commands are registered by which plugin.

## How this documentation works

This documentation is split into the major sections that build up the CommandAPI. It's been designed in such a way that it should be easy to find exactly what you want to help you get started with the CommandAPI, and how to make effective use of it. Each step of the way, the documentation will include examples which showcase how to use the CommandAPI.

You can use the sidebar on the left to access the various sections of the documentation.

Using the search icon in the top left corner, you can search for anything in this entire documentation. For example, typing "Example" will show a list of examples which are included throughout the documentation.

## Latest documentation changes ( `9.7.0 → 10.0.0` ) {#latest-changes}
- ??? (Coming soon????)
