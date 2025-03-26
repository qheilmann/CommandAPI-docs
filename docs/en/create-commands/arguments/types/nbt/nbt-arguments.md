---
order: 1
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# NBT arguments

The CommandAPI has support for arguments that interact with Minecraft's [NBT format](https://minecraft.wiki/w/NBT_format). Since Minecraft's NBT format is not directly accessible via Spigot (it is instead implemented via Spigot's APIs when necessary), using NBT-based arguments requires an implementation of an NBT API that is capable of interpreting NMS NBT objects.

## Shading usage setup

In order to use the `NBTCompoundArgument`, you can an NBT API that can create an NBT Compound object from an `Object` (ideally a `net.minecraft.nbt.NBTTagCompound` object). Examples of NBT APIs that can do this are _(these are not sponsored in any way)_:

- Mojang-mapped plugins, via `net.minecraft.nbt.CompoundTag`
- [NBT API](https://www.spigotmc.org/resources/nbt-api.7939/)
- [Rtag](https://www.spigotmc.org/resources/100694/)

## Hooking into an NBT API

Before the `NBTCompoundArgument` can be used, the CommandAPI needs to know what implementation of an NBT Compound object you're going to use. This is specified in the `onLoad()` sequence, where your CommandAPI's config is set up, by using the following method:

```java
<T> CommandAPIConfig initializeNBTAPI(Class<T> nbtContainerClass, Function<Object, T> nbtContainerConstructor);
```

The `initializeNBTAPI(Class<T>, Function<Object, T>)` takes in two arguments:

- `Class<T>` - The class that will be your NBT Compound implementation. This is also the type that the CommandAPI will return when the `NBTCompoundArgument` is used.

- `Function<Object, T>` - A function that takes in an object and returns the specified NBT Compound implementation. This could be a constructor or a static method, for example.

## Using Minecraft's NBT objects

The CommandAPI allows you to access the NMS `NBTTagCompound` class via `net.minecraft.nbt.NBTTagCompound` directly without requiring any additional APIs to access NBT. This is especially useful for Mojang-mapped servers.

| Spigot-mapped name | Mojang-mapped name |
|--------------------|--------------------|
| `NBTTagCompound`   | `CompoundTag`      |
| `NBTBase`          | `Tag`              |

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#hookNbtAPIExampleMojang
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#hookNbtAPIExampleMojang
:::

::::tip Example – Using a Mojang-mapped `CompoundTag`

Since the underlying implementation of the `NBTCompoundArgument` can change (for example, `CompoundTag` if you're using Mojang-mapped names), the type of your NBT compound implementation has to be declared in angle brackets.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#nbtCompoundArgumentsExampleMojang
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleMojang
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleMojangDSL
:::

::::
