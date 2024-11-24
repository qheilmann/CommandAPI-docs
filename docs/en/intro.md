---
order: 1
outline: 2
authors:
  - JorelAli
  - DerEchtePilz
---

# Introduction

Welcome to the documentation for the CommandAPI. The CommandAPI lets you create vanilla Minecraft commands which utilize the new command features which were implemented in Minecraft 1.13, including but not limited to:

- Having commands compatible with the vanilla `/execute` command
- Having commands which can be run using Minecraft functions
- Having better auto-completion and suggestions
- Having command type checks before execution (e.g. ensuring a number is within a certain range)

## How the CommandAPI works

::: info
This is a pretty important section, I would recommend reading before implementing the CommandAPI in your own projects. This section tells you about things which is not stated anywhere else in the documentation. Think of it as the "knowledge you should know before using this API".
:::

The CommandAPI does not follow the "standard" method of registering commands. In other words, commands which are registered with the CommandAPI will be registered as pure vanilla Minecraft commands as opposed to Bukkit or Spigot commands. This means that the following implications exist:

- **Commands should not be declared in the `plugin.yml` file.**
- Commands are automatically registered under the `minecraft` namespace. For example, if you register a command `/hello`, you can also run it using `/minecraft:hello`. However, you can change this default `minecraft` namespace. More about this [on the command registration page](./create-commands/registration#registering-the-command).
- Commands are not "linked" to a certain plugin. In other words, you cannot look up which commands are registered by which plugin.

## How this documentation works

This documentation is split into the major sections that build up the CommandAPI. It's been designed in such a way that it should be easy to find exactly what you want to help you get started with the CommandAPI, and how to make effective use of it. Each step of the way, the documentation will include examples which showcase how to use the CommandAPI.

You can use the sidebar on the left to access the various sections of the documentation.

Using the search icon in the top left corner, you can search for anything in this entire documentation. For example, typing "Example" will show a list of examples which are included throughout the documentation.

## Documentation changelog

Here's the list of changes to the documentation between each update. You can view the current documentation version at the top of this page.

### 9.4.0 → 9.4.1

- Updates [Configuration for server owners](./user-setup/config) page with new `skip-initial-datapack-reload` option.

### 9.3.0 → 9.4.0

- Updates [Shading the CommandAPI in your plugins](./dev-setup/shading) page with spigot-mapped and mojang-mapped dependencies
- Adds [Velocity](./velocity_intro) page to outline how to set up the CommandAPI for Velocity
- Updates [CommandArguments](./create-commands/arguments/command-arguments) to document new additions for safe arguments
- Updates [Potion effect arguments](./argument_potion) to include examples for the newly added `NamespacedKey` variant for the `PotionEffectArgument`
- Updates [Arguments](./create-commands/arguments/command-arguments) to list the newly added `PotionEffectArgument.NamespacedKey` argument
- Updates [Particles](./argument_particles) page to include both [old particle information](./argument_particle_old) and [new particle information](./argument_particle_new)

### 9.2.0 → 9.3.0

- Updates [Incompatible version information](./incompatibleversions) page and `FunctionArgument` pages that functions made with the `FunctionArgument` will always return 1 when running on 1.20.3 and 1.20.4.
- Updates [Kotlin DSL](./kotlindsl) page to mention every possible executor method.

### 9.1.0 → 9.2.0

- Updates [Location arguments](./argument_locations) page to mention the new `centerPosition` argument to center the position of integer values for location arguments.
- Added [Delegated properties](./delegated_properties) page to mention the added support of delegations.

### 9.0.3 → 9.1.0

- Updates [Multi literal arguments](./argument_multiliteral) page to mention new `MultiLiteralArgument` constructor.
- Adds [CommandArguments](./create-commands/arguments/command-arguments) page to explain the `CommandArguments` class introduced in 9.0.0
- Adds [Adventure chat color argument](./argument_chat_adventure) to the adventure chat arguments page.
- Adds new [Command unregistration](./create-commands/unregistration) page.

### 9.0.2 → 9.0.3

- Updates [Map arguments](./argument_map) page with new syntax

### 9.0.1 → 9.0.2

- Updates [Multi literal arguments](./argument_multiliteral) page to add the node name information
- Updates [Help](./help) page so it contains `withUsage()` information

### 9.0.0 → 9.0.1

- Updates [Optional arguments](./optional_arguments) page to update the method list for avoiding `null` values
- Updates [Normal command executors](./create-commands/executors/normal-executors) page to now mention the existence of the `ExecutionInfo`

### 8.8.0 → 9.0.0

::: info
9.0.0 is a giant update that is incompatible with 8.8.x and prior versions. A lot of the documentation's code examples and explanations have been changed for the various changes made in this version. Please read the [Upgrading guide](./upgrading) for information on how to upgrade to 9.0.0.
:::

- Adds the new [Optional arguments](./optional_arguments) section
- Adds Kotlin DSL code examples to all code examples

### 8.7.0 → 8.8.0

- Changed the version number from 8.7.0 to 8.8.0. That's it.

### 8.6.0 → 8.7.0

::: info
`SoundArgument`s written for 8.6.0 are incompatible with this update! Other significant changes to `ScoreHolderArgument` and `EntitySelectorArgument` were made in this update. I highly recommend reading the [Upgrading guide](./upgrading) section which covers the changes in more detail and how to update your plugin for this version.
:::

- Updated [Biome arguments](./argument_biome) to allow for `NamespacedKey` objects
- Updated [Sound arguments](./argument_sound) with the new `NamespacedKey` constructor
- Updated [Scoreboard arguments](./argument_scoreboards) with the new `Single` and `Multiple` constructors
- Updated [Entity & player arguments](./argument_entities) to include the new `OneEntity`, `ManyEntities`, `OnePlayer` and `ManyPlayers` constructors

### 8.5.1 → 8.6.0

- Greatly improved the format and documentation for [Configuration for server owners](./user-setup/config)
- Adds a Kotlin tab to all Java code blocks which displays the equivalent code, but in Kotlin
- Adds the new [Command arguments](./create-commands/arguments/command-arguments) section
- Adds the new [World arguments](./argument_world) section
- Mentions the new `LiteralArgument.of()` and `LiteralArgument.literal()` methods in [Literal arguments](./argument_literal)
- Adds a really cool new example to the [Brigadier Suggestions](./brigadiersuggestions) page
- Updated various sections (a summary of this can be found in the [Upgrading guide](./upgrading)):
  - Update [List arguments](./argument_list) to include the new `buildGreedy()` and `buildText()` methods
  - Update [Handling command failures](./create-commands/executors/handle-failures) with new methods
  - Update [Argument suggestions with tooltips](./tooltips) with new tooltip methods for formatting text
  - Update [Sound arguments](./argument_sound) with support for namespaced keys
- Adds documentation for [Kotlin-based commands](./kotlinintro) using the Kotlin DSL
- Update the [Afterword](./afterword), giving special credits to some very special contributors!

### 8.5.0 → 8.5.1

- Update [Brigadier + CommandAPI](./brigadier) with function parameter changes. See [Upgrading](./upgrading) for more info.

### 8.4.0 → 8.5.0

- Adds [Chat preview](./chatpreview) section
- Adds Kotlin-DSL `build.gradle.kts` instructions for using the CommandAPI
- Adds `CommandAPI.onDisable()` method to [Shading the CommandAPI in your plugins](./dev-setup/shading#enabling-disabling)

### 8.3.0 → 8.4.0

- Updated [Shading with Maven](./dev-setup/shading) with updated `maven-shade-plugin` version
- Adds [NamespacedKey arguments](./argument_namespacedkey) section
- Update [Argument Casting](./arguments#argument-casting) section with new arguments and types
- Update [NBT arguments](./argument_nbt) page with new NBT arguments information
- Update [Custom arguments](./argument_custom) page with new custom arguments information
- Adds [Getting a list of registered commands](./internal#getting-a-list-of-registered-commands) section to the Internal CommandAPI page
- Update [Upgrading guide](./upgrading) for 8.4.0 changes

### 8.2.0 → 8.2.1

- Adds `withSubcommands` method to [Subcommands](./subcommands) section.

### 8.0.0 → 8.2.0

- Adds [List arguments](./argument_list) section.
- Fix bug with [Multiple command executors with the same implementation](./normalexecutors#multiple-command-executors-with-the-same-implementation) example.

### 7.0.0 → 8.0.0

- Updated particle arguments in the [Particle arguments](./argument_particles) section.
- Update the [Upgrading guide](./upgrading) for the new changes in 8.0.0.

### 6.5.4 → 7.0.0

- Changed the repo that the CommandAPI is served from JitPack to Maven Central.
- Remove direct link to `CommandAPI.jar` file from [Installation for server owners](./user-setup/install), in favor of pointing to the latest release page (to allow version numbers to appear in the file name).
- Rewrite the [Argument suggestions](./argumentsuggestions) section to cover the new argument suggestions API.
- Update the [Upgrading guide](./upgrading) for the new changes in 7.0.0.
- Update repository information in the [Shading the CommandAPI in your plugins](./dev-setup/shading) page.
- Update the [Brigadier + CommandAPI](./brigadier) page with updated methods.
- Adds an example of using Brgiader's `SuggestionsBuilder` in the [Brigadier Suggestions](./brigadiersuggestions) section.
- Updated the colors of links, example blocks and warning blocks to meet accessibility contrast guidelines better.
- Adds [Command trees](./commandtrees) section.
- Update [Handling command failures](./create-commands/executors/handle-failures) to fit new `throw` requirement for command failures.
- Updated [Normal command executors](./create-commands/executors/normal-executors#multiple-command-executors-with-the-same-implementation) with the new multiple command executors with the same implementation feature.

### 6.4.0 → 6.5.4

- Update the Maven and Gradle pages to say to use CommandAPI version 6.5.4 because this kept confusing everyone.

### 6.3.1 → 6.4.0

- Adds a section [Help](./help) for the new help feature.
- Update [Annotations](./annotations) section to include new `@Help` annotation.

### 6.3.0 → 6.3.1

- Adds Java 16 error to [Troubleshooting](./troubleshooting).
- Update [FAQ](faq).
- Adds some useful tools to [Command conversion](user-setup/command-conversion/conversion) to get plugin info and check `config.yml` validity.
- Adds [Plugin reloading](./reloading) page which describes how to add minimal support for `/reload`.

### 6.2.0 → 6.3.0

- Update [Custom arguments](./argument_custom) page with new custom argument constructor information
- Adds upgrade info to the [Upgrading guide](./upgrading#from-version-620-to-630) to help upgrade any existing custom arguments which you may have.

### 6.0.0 → 6.2.0

- Update [Configuration for server owners](./user-setup/config) page with new config options `missing-executor-implementation` and `use-latest-nms-version`
- Update instructions for shading the CommandAPI with maven in [Shading the CommandAPI in your plugins](./dev-setup/shading) to support Java 16.
- Mention that commands registered with the CommandAPI appear in the `minecraft:` namespace (see above under "How the CommandAPI works")

### 5.12 → 6.0.0

- Adds entry for [Upgrading guide](./upgrading#from-version-5x-to-600) to help update from 5.12 to 6.0.0.
- Adds new `silent-logs` config entry to [Configuration for server owners](./user-setup/config)
- Update syntax for `onLoad(CommandAPIConfig)` for [Shading the CommandAPI in your plugins](./dev-setup/shading)
- Update [Argument suggestions](./argumentsuggestions) including new `replaceSuggestions` method
- Adds documentation for [OfflinePlayerArgument](./argument_entities#offlineplayer-argument)
- Adds a new section **CommandAPI Contribution** which gives a bit of insight into the project structure of the CommandAPI (Still in progress, not complete yet)
- Fix old documentation typos
- Fix spacing issues in some existing code blocks
- Adds syntax highlighting for Minecraft commands in code blocks
- Fix old code examples which didn't work anymore

### 5.11 → 5.12

::: info
The Maven/Gradle repository URL has changed! See [5. Setting up your development environment](./dev-setup/setup) for more information. For older versions of the CommandAPI (versions 5.11 and below), please consult the older documentation which can be found on the homepage [here](https://commandapi.jorel.dev/).
:::

- Change the repository URL for the CommandAPI in [5. Setting up your development environment](./dev-setup/setup)
- Update the [Afterword](./afterword)

### 5.10 → 5.11

- Adds a section [Arbitrary command conversion](user-setup/command-conversion/single-command#arbitrary-command-conversion) on how to convert arbitrary commands
- Adds a section [3.3. Entity selectors](user-setup/command-conversion/entity-selectors) describing how to convert entity selector commands with the CommandAPI's command conversion system
- Updated the [list of all supported argument types](user-setup/command-conversion/single-command-with-args#list-of-all-supported-argument-types) for command conversion

### 5.6 → 5.10

- Splits chat argument sections into two: [11.5.1. Spigot chat arguments](./argument_chat_spigot) and [11.5.2. Adventure chat arguments](./argument_chat_adventure)
- Adds a [FAQ page](./faq)
- Adds a warning about shading in [6. Shading the CommandAPI in your plugins](./dev-setup/shading)

### 5.3 → 5.6

- Adds a section [4. Skipping proxy senders](user-setup/command-conversion/skip-proxy-senders) which describes how to use the `skip-sender-proxy` configuration option.

### 5.2 → 5.3

- Adds a section [6. Using the annotation system](./setup_annotations) on setting up your development environment to use the annotation system
- Adds a whole massive section on using annotations ([16. Annotation-based commands](./annotationsintro), [17. Annotations](./annotations), [18. Registering annotation-based commands](./registeringannotations))
- Adds a section on argument suggestion deferral in section [9.1. Argument suggestions](./argumentsuggestions#argument-suggestion-deferral)
- Improve warning for `LiteralArgument` - instead of it being "obsolete" compared to the `MultiLiteralArgument`, it is now "more complex" than `MultiLiteralArgument`s
- Fix issue in the section for custom arguments which should have been updated but wasn't

### 5.1 → 5.2

- Adds brief documentation for the CommandAPI's `reloadDatapacks` method in [16. Internal CommandAPI](./internal#reloading-datapacks)

### 5.0 → 5.1

- Adds a section [10.2. The SimpleFunctionWrapper class](./simplefunctionwrapper) which outlines the new `SimpleFunctionWrapper` class
- Updates the documentation for [10.3. The FunctionWrapper class](./functionwrapper)
- Update the name of the package from `dev.jorel.commandapi.CommandAPIHandler.Brigadier` to `dev.jorel.commandapi.Brigadier` in section [17. Brigadier + CommandAPI](./brigadier#brigadier-support-functions)
- Update the documentation for [11. Permissions](./permissions) stating that you can use `withPermission(String)` instead of `withPermission(CommandPermission)`

### 4.3 → 5.0

::: info
Lots and lots and lots of changes occurred in version 5.0! I highly recommend reading the [Upgrading guide](./upgrading) section which covers the changes in more detail and how to update your plugin for this version.
:::

Every page has been rewritten in this update and checked for errors. In general, this is the list of new additions:

- New section [3. Command conversion](user-setup/command-conversion/conversion) dedicated to command conversion via the `config.yml`
- New section [8.4. Listed arguments](./listed)
- New section [9.8.1. Angle arguments](./argument_angle)
- New section [14. Subcommands](./subcommands)
- New section [16. Internal CommandAPI](./internal) now lists all arguments and their respective Minecraft argument IDs
- Mentions listed arguments in section [9.11.1. Literal arguments](./argument_literal)
- Section [15. Command conversion](./conversion) has been rewritten
- Executes native is now present in the command registration page
- Section [8.3. Argument suggestions with tooltips](./tooltips) now mentions the `IStringTooltip` class

### 4.2 → 4.3

- Improve the documentation for [2. Configuration for server owners](./user-setup/config) by using simple YAML lists (using the `-` symbol) and update the command conversion syntax for all commands using the `~` syntax
- Adds the command sender priority to [6.1. Normal command executors](./create-commands/executors/normal-executors#multiple-command-executor-implementations)
- Adds new method and example for converting specific commands internally in [13. Command conversion](./conversion#only-specific-commands)
- Adds two sneaky little buttons in the main title toolbar at the top of the page

### 4.1 → 4.2

- Adds a warning about using redirected commands for suggestions that depend on previous arguments in [7.1. Argument suggestions](./argumentsuggestions#suggestions-depending-on-previous-arguments)
- Adds a new section [6.3. Native CommandSenders](./create-commands/executors/native-sender)
- Update documentation for [6.1. Normal command executors](./create-commands/executors/normal-executors#restricting-who-can-run-your-command) to include the `.executesNative()` method for native command senders

### 4.0 → 4.1

- Adds a new section [7.3. Argument suggestions with tooltips](./tooltips)
- Adds documentation for the `MultiLiteralArgument` in section [8.11.2. Multi literal arguments](./argument_multiliteral)
- Adds a new section [4. Shading the CommandAPI into your plugins](./dev-setup/shading)
- Update documentation for [14. Brigadier + CommandAPI](./brigadier) with new (overloaded) function `argBuildOf`
- Update [Afterword](./afterword)

### 3.4 → 4.0

- Update the maven and gradle documentation to state that it is `provided` and `compileOnly`
- The project has been renamed from the "1.13 Command API" to simply the "CommandAPI". This has changed a few things, such as various links. See the section [Upgrading guide](./upgrading) to view the relevant changes in regard to maven.
- Updated [3. Setting up your development environment](./dev-setup/setup) to include new Maven repository links
- Fixed stronkage with Java versions - there's now no random warning boxes about incompatibility with Java 12!
- Arguments now include pictures that showcase how they work!
- Reorganised the sections - arguments is now split up into two sections: [6. Arguments (in general)](./create-commands/arguments/command-arguments) and [7. Argument types](./argumenttypes)
- Adds documentation for [6.2. Safe argument suggestions](./safeargumentsuggestions)
- Adds documentation for [7.8.3. BlockState arguments](./argument_blockstate)
- Adds documentation for new arguments:
  - `UUIDArgument`: [7.8.14. UUID arguments](./argument_uuid)
  - `BlockPredicateArgument`: [7.9.1. Block predicate arguments](./argument_blockpredicate)
  - `ItemStackPredicateArgument`: [7.9.2. ItemStack predicate arguments](./argument_itemstackpredicate)
- Adds page [Incompatible version information](./incompatibleversions) outlining what parts of the CommandAPI are incompatible with what versions of Minecraft
- Adds `getCommands()` documentation to the [8.2. The FunctionWrapper class](./functionwrapper#getcommands) page
- Adds page [13. Brigadier + CommandAPI](./brigadier) which shows how the CommandAPI can be used with Brigadier side-by-side
- Adds page [10. Requirements](./requirements) for the requirements feature to help restrict commands
- Adds page [14. Predicate tips](./predicatetips) with information on how to reduce the amount of repeated code when using requirements
- Update [Afterword](./afterword)

### 3.3 → 3.4

- Moves configuration for server owners to a new section [2. Configuration for server owners](./user-setup/config). This has the side effect of also re-numbering all the sections on the left. Sorry!
- Adds server owner documentation for the CommandAPI's config command conversion system in section [2. Configuration for server owners](./user-setup/config)
- Update the conversion page [10. Command conversion](./conversion) so it should be much easier to follow and understand
- Changed the list of Java versions that the CommandAPI is compatible with in the [Troubleshooting](./troubleshooting#commandapi-errors-when-reloading-datapacks) section

### 3.1 → 3.3

- Adds information on how functions are loaded in 1.16+ in section [6. Functions & Tags](./functions#functions-in-116)
- Added function error messages to section [Troubleshooting](./troubleshooting#server-errors-when-loading-datapacks-in-116)

### 3.0 → 3.1

- Adds new section [5.1 Argument suggestions](./argumentsuggestions) to cover how to override suggestions - Having it all in section _5. Arguments_ was a bit too content-heavy
- Adds documentation for the new `.overrideSuggestions()` method in section [5.1 Argument suggestions](./argumentsuggestions#suggestions-depending-on-previous-arguments)
- Simplified the description of the documentation updates
- Changed the artifact ID for the dependency of the CommandAPI. Instead of being `commandapi`, it is now `commandapi-core`. You can view the changes in section [2 Setting up your development environment](./dev-setup/setup)
- Changed the repository information for gradle in section [2 Setting up your development environment](./dev-setup/setup). You now have to include the NBTAPI repository because gradle can't automatically detect this for some reason. Kinda stupid tbh.
- Adds a section on using multiple or optional arguments in section [5 Arguments](./create-commands/arguments/command-arguments)

### 2.1 → 3.0

::: info
Lots of changes occurred in version 3.0. I highly recommend reading the [Upgrading guide](./upgrading) section which covers the changes in more detail and how to update your plugin for this version.
:::

- Sections on the left have been tidied up and should be more "approachable"
- Installation section ([1. Installation for server owners](./user-setup/install)) now includes information about additional dependencies
- Dependency section ([2. Setting up your development environment](./dev-setup/setup)) updated to use the new dependency Group ID
- Command registration section ([3. Command registration](./create-commands/registration)) updated to reflect new API changes
- Command execution section ([4. Command Executors](./create-commands/executors/command-executors)) updated to reflect new API changes
- Arguments section ([5. Arguments](./create-commands/arguments/command-arguments)) completely rewritten to reflect new API changes. Adds more detailed examples for each argument
- Function arguments section ([6.3 Function Arguments](./argument_function)) updated to reflect new API changes
- Permissions section ([7. Permissions](./permissions)) updated to reflect new API changes
- Aliases section ([8. Aliases](./aliases)) updated to reflect new API changes
- Command conversion section ([9. Command conversion](./conversion)) rewrite example to be more detailed
