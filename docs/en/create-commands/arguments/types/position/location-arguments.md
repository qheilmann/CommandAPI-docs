---
order: 1
authors:
  - JorelAli
  - DerEchtePilz
---

# Location arguments

![A Location argument showing the options '~', '~ ~' and '~ ~ ~'](/images/arguments/loc.png)

In the CommandAPI, there are two arguments used to represent location. The `LocationArgument` argument, which represents a 3D location $\\(x, y, z\\)$ and the `Location2DArgument`, which represents 2D location $\\(x, z\\)$.

## 3D Location

The `LocationArgument` class is used to specify a location in the **command sender's current world**, returning a Bukkit `Location` object. It allows the user to enter three numbers as coordinates, or use relative coordinates (i.e., the `~` and `^` operators).

The `LocationArgument` constructor requires a `LocationType`, which specifies the type of location that is accepted by the command. The `LocationType` enum consists of two values:

### `LocationType.BLOCK_POSITION`

`BLOCK_POSITION` refers to integer block coordinates. When in-game as a player, the suggested location is the coordinates of block you’re looking at when you type the command.

![BLOCK_POSITION](/images/arguments/locationargument_blockposition.png)

### `LocationType.PRECISE_POSITION`

`PRECISE_PRECISION` uses exact coordinates, using the `double` primitive type. When in-game as a player, the suggested location is the exact coordinates of where your cursor is pointing at when you type the command.

![PRECISE_POSITION](/images/arguments/locationargument_preciseposition.png)

If no `LocationType` is provided, **the `LocationArgument` will use `PRECISE_POSITION` by default**.

The `LocationArgument` constructor can also accept a `boolean centerPosition`. If set to `true`, when using `LocationType.PRECISE_POSITION`, if an integer is provided in the value, it will add 0.5 to the x and z coordinates to center the position within a block. If set to `false`, the integer value will be provided as is.

If no `centerPosition` parameter is provided, **the `LocationArgument` will use `centerPosition = true` by default**.

::::tip Example – LocationArgument precise position centering

Say you use the following constructor, which sets `centerPosition` to `true`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/position/LocationArguments.java#centerPositionExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/position/LocationArguments.kt#centerPositionExample
:::

**Integer positions are centered**

Let's also say you use the following location using this location argument in a command:

```text
10 20 30
```

The resulting location will be the following, which centers the position of the x and z coordinates. This doesn’t change the y coordinate:

```text
10.5 20 30.5
```

**Non-integer positions remain as normal**

If you use the following location using this location argument in a command:

```text
10.2 20.2 30.2
```

The resulting location will be the following, which doesn’t change the x and z coordinates, because the positions aren’t integers:

```text
10.2 20.2 30.2
```

**Integer positions are not centered**

Say you use the following constructor, which sets `centerPosition` to `false`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/position/LocationArguments.java#doNotCenterPositionExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/position/LocationArguments.kt#doNotCenterPositionExample
:::

**Integer positions are not centered**

Let's also say you use the following location using this location argument in a command:

```text
10 20 30
```

The resulting location will be the following, which doesn’t modify the position of the x and z coordinates:

```text
10 20 30
```

::::

::::tip Example – Break block using coordinates

We can declare a simple command to break a block:

```mccmd
/break <location>
```

Simply put, given the coordinates provided to the command, "break" the block by setting its type to `Material.AIR`. For this example, we're referring to block specific coordinates, so we want to use `LocationType.BLOCK_POSITION`:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/position/LocationArguments.java#breakCommandExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/position/LocationArguments.kt#breakCommandExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/position/LocationArguments.kt#breakCommandExampleDSL
:::

::::

## 2D Location

![A location 2D argument showing the options '~' and '~ ~'](/images/arguments/loc2d.png)

The `Location2DArgument` is pretty much identical in use to the `LocationArgument` for 3D coordinates, except instead of returning a `Location` object, it instead returns a `Location2D` object that extends `Location` (thus, being compatible anywhere you would normally be able to use `Location`).