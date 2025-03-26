---
order: 3
authors:
  - JorelAli
---

# RTag

[Rtag](https://www.spigotmc.org/resources/100694/) is a shadeable API that allows you to easily interact with NBT. There are multiple ways to deconstruct NBT arguments using Rtag.

## Accessing individual paths using `Rtag.INSTANCE.get()`

The [`Rtag#get`](https://javadoc.saicone.com/rtag/com/saicone/rtag/Rtag.html#get(java.lang.Object,java.lang.Object...)) method allows you to retrieve objects directly from an NMS `NBTTagCompound` object without requiring you to import `net.minecraft.net.NBTTagCompound`. We can initialize an NBTAPI by using an `Object` class, and using the identity function (to take the NMS object from the CommandAPI and keep it as an NMS object):

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#hookNbtAPIExampleRtag
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#hookNbtAPIExampleRtag
:::

::::tip Example - Accessing NBT compound tags directly with `Rtag#get`

For example, if you have an NBT compound tag with this data:

```js
{
    some: {
        path: {
            here: "hello"
        }
    }
}
```

You can access the string value `hello` using `Rtag.INSTANCE.get()`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#nbtCompoundArgumentsExampleRtagPath
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleRtagPath
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#nbtCompoundArgumentsExampleRtagPathDSL
:::

::::

## Converting a compound tag into standard Java objects

The easiest way to interact with Rtag is to deconstruct a compound tag into a `Map` of Java objects. Rtag is able to do this easily using `TagCompound#getValue`, with the `Rtag.INSTANCE` field, allowing you to initialize an NBT API as follows:


:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/NBTArguments.java#hookNbtAPIExampleRtagObjects
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/NBTArguments.kt#hookNbtAPIExampleRtagObjects
:::

Say you have an NBT compound tag that looks like this:

```js
{
    name: "Notch",
    exp: 20s,
    armor: ["diamond_helmet", "elytra", "diamond_leggings", "leather_boots"],
    hand_item: {
        type: "diamond_pickaxe",
        durability: 123
    }
}
```

Using Rtag's representation of an NBT compound tag using standard Java objects would convert the above NBT compound tag into the following object:

```java
Map<String, Object> {
    "name": String "Notch",
    "exp": Short 20,
    "armor": List<String> ["diamond_helmet", "elytra", "diamond_leggings", "leather_boots"],
    "hand_item": Map<String, Object> {
        "type": String "diamond_pickaxe",
        "durability": Integer 123
    }
}
```
