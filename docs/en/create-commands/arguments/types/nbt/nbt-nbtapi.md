---
order: 2
authors:
  - JorelAli
---

# NBT API

[NBT API](https://www.spigotmc.org/resources/nbt-api.7939/) is an API that can be run as a standalone plugin on a server, or shaded into your own plugin to allow you to easily interact with NBT.

## Setup

The NBT API can be shaded into your project as stated in its official documentation:

- Shading using [Maven](https://github.com/tr7zw/Item-NBT-API/wiki/Using-Maven#option-2-shading-the-nbt-api-into-your-plugin)
- Shading using [Gradle](https://github.com/tr7zw/Item-NBT-API/wiki/Using-Gradle#option-2-shading-the-nbt-api-into-your-plugin)

You can configure the CommandAPI using the `CommandAPI.onLoad()` method to use the `ReadWriteNBT` class using the [`NBT#wrapNMSTag`](https://tr7zw.github.io/Item-NBT-API/v2-api/de/tr7zw/changeme/nbtapi/NBT.html#wrapNMSTag-java.lang.Object-) method that wraps `net.minecraft.nbt.CompoundTag` objects into NBTAPI `ReadWriteNBT` objects:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#hookNbtAPIExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#hookNbtAPIExample
:::

## Using `ReadWriteNBT` to handle NBT

::::tip Example – Reading NBT Compounds using `ReadWriteNBT`

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#nbtCompoundArgumentsExampleNBTAPI
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleNBTAPI
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleNBTAPIDSL
:::

::::
