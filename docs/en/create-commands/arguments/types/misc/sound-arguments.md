---
order: 13
authors:
  - DerEchtePilz
  - JorelAli
  - willkroboth
---

# Sound arguments

![A sound argument command with a list of Minecraft sounds as suggestions](/images/arguments/sound.png)

The `SoundArgument` class allows a command sender to retrieve the Bukkit `Sound` or `NamespacedKey` object to represent in-game sound effects (such as mob sounds or ambient sound effects), as well as in-game music.

The `SoundArgument` can return a `Sound` or `NamespacedKey` object. To return a `Sound` object, simply use the `SoundArgument` as normal. To return a `NamespacedKey` object, use the `SoundArgument.NamespacedKey` constructor instead:

```java
// Makes a SoundArgument that returns a Sound
new SoundArgument("sound");

// Makes a SoundArgument that returns a NamespacedKey
new SoundArgument.NamespacedKey("sound");
```

:::danger

When using the `Sound` object, the CommandAPI will return `null` if the specified `Sound` could not be found. For this reason, it's recommended to use the `NamespacedKey` object for optimal compatibility with client-side resourcepacks.

:::

::::tip Example – Playing sound to yourself

Say we want a simple command that plays a specific sound at your location. To do this, we will make the following command:

```mccmd
/sound <sound>
```

This command simply plays the provided sound to the current player:

#### Use `SoundArgument`

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/SoundArguments.java#soundArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/SoundArguments.kt#soundArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/SoundArguments.kt#soundArgumentsExampleDSL
:::

#### Use `SoundArgument.NamespacedKey`

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/SoundArguments.java#soundArgumentsNamespacedKeyExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/SoundArguments.kt#soundArgumentsNamespacedKeyExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/SoundArguments.kt#soundArgumentsNamespacedKeyExampleDSL
:::

::::