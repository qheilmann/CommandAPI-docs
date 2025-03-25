---
order: 11
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# NBT arguments

The CommandAPI includes support for NBT compound arguments using an NBT API. The usage for the `NBTCompoundArgument` depends on whether you are using the CommandAPI plugin (using a `CommandAPI.jar` file in your `plugins/` folder), or are shading the CommandAPI (including the compiled CommandAPI code in your own plugin).

## Shading usage setup

In order to use the `NBTCompoundArgument`, you will have to use an NBT API that can create an NBT Compound object from an `Object` (ideally a `net.minecraft.nbt.NBTTagCompound` object). Examples of NBT APIs that can do this are _(these are not sponsored in any way)_:

- [NBT API](https://www.spigotmc.org/resources/nbt-api.7939/), via the [`new NBTContainer(Object)`](https://tr7zw.github.io/Item-NBT-API/v2-api/de/tr7zw/changeme/nbtapi/NBTContainer.html#NBTContainer-java.lang.Object-) constructor

### Hooking into an NBT API

Before the `NBTCompoundArgument` can be used, the CommandAPI needs to know what implementation of an NBT Compound object you're going to use. This is specified in the `onLoad()` sequence, where your CommandAPI's config is set up, by using the following method:

```java
<T> CommandAPIConfig initializeNBTAPI(Class<T> nbtContainerClass, Function<Object, T> nbtContainerConstructor);
```

The `initializeNBTAPI(Class<T>, Function<Object, T>)` takes in two arguments:

- `Class<T>` - The class that will be your NBT Compound implementation. This is also the type that the CommandAPI will return when the `NBTCompoundArgument` is used.

- `Function<Object, T>` - A function that takes in an object and returns the specified NBT Compound implementation. This could be a constructor or a static method, for example.

::::tip Example – Hooking into the NBT API

Say we want to use the [NBT API](https://www.spigotmc.org/resources/nbt-api.7939/) as our implementation of NBT compounds. First, we have to shade the NBT API into our project (view the official documentation for how to do this for [Maven](https://github.com/tr7zw/Item-NBT-API/wiki/Using-Maven#option-2-shading-the-nbt-api-into-your-plugin) or [Gradle](https://github.com/tr7zw/Item-NBT-API/wiki/Using-Gradle#option-2-shading-the-nbt-api-into-your-plugin)).

Now, we can configure the CommandAPI using the `CommandAPI.onLoad()` method to use the `NBTContainer` class, and the [`NBTContainer` constructor that takes in an `Object`](https://tr7zw.github.io/Item-NBT-API/v2-api/de/tr7zw/changeme/nbtapi/NBTContainer.html#NBTContainer-java.lang.Object-):

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#hookNbtAPIExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#hookNbtAPIExample
:::

> _Confused with the `::new` syntax? Read more about method references to a constructor [here](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)._

We're now able to use the `NBTContainer` as our implemented type for the `NBTCompoundArgument`!

::::

::::tip Example – ???

Since the underlying implementation of the `NBTCompoundArgument` can change (e.g. `NBTContainer` if you're using the NBT API), the type of your NBT compound implementation has to be declared in angle brackets.

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#nbtCompoundArgumentsExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleDSL
:::

::::

:::info

If you believe you can supply a suitable example for this page, feel free to send an example [on the CommandAPI issues page!](https://github.com/CommandAPI/CommandAPI/issues/new/choose)

:::