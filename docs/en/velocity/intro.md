---
order: 1
preferences: ['build-system']
authors:
  - JorelAli
  - DerEchtePilz
---

# Velocity

:::info **Developer's Note:**

This section assumes you are already familiar with how to set up a Velocity plugin.

:::

## Adding the dependency

Add the dependency to your list of dependencies in your build script:

<div class="maven">

```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-velocity-shade</artifactId>
        <version>10.0.0</version>
    </dependency>
</dependencies>
```

</div>
<div class="gradle">

<div class="groovy">

```groovy
dependencies {
    implementation "dev.jorel:commandapi-velocity-shade:10.0.0"
}
```

</div>
<div class="kts">

```kotlin
dependencies {
    implementation("dev.jorel:commandapi-velocity-shade:10.0.0")
}
```

</div>

</div>

## Setting up the CommandAPI

The CommandAPI requires two steps: loading and enabling. We will perform these steps in Velocity's loading stages, construction and initialization. These two stages are explained in [their documentation](https://docs.papermc.io/velocity/dev/api-basics#a-word-of-caution).
We will perform the CommandAPI's loading step in the construction phase first:

<<< @/../reference-code/src/main/java/velocity/Intro.java#loadCommandAPIExample

Next, we want to utilise Velocity's `ProxyInitializeEvent` to perform the CommandAPI's enabling step:


<<< @/../reference-code/src/main/java/velocity/Intro.java#enableCommandAPIExample

## Current limitations

The CommandAPI currently only offers support for a very limited number of arguments on Velocity. This is because arguments are primarily implemented on the backend servers.
However, the CommandAPI offers access to the primitive type arguments:

- [`IntegerArgument`](../create-commands/arguments/types/primitive-arguments#numerical-arguments)
- [`LongArgument`](../create-commands/arguments/types/primitive-arguments#numerical-arguments)
- [`FloatArgument`](../create-commands/arguments/types/primitive-arguments#numerical-arguments)
- [`DoubleArgument`](../create-commands/arguments/types/primitive-arguments#numerical-arguments)
- [`BooleanArgument`](../create-commands/arguments/types/primitive-arguments#boolean-arguments)
- [`StringArgument`](../create-commands/arguments/types/string-arguments#string-argument)
- [`TextArgument`](../create-commands/arguments/types/string-arguments#text-argument)
- [`GreedyStringArgument`](../create-commands/arguments/types/string-arguments#greedy-string-argument)
- [`LiteralArgument`](../create-commands/arguments/types/literal/literal-arguments)
- [`MultiLiteralArgument`](../create-commands/arguments/types/literal/multiliteral-arguments)

## Registering a simple command

Command registration works the same way as it does in Bukkit. To visualize this, we want to register a simple command that generates a random number between a chosen minimum and a chosen maximum value:

::::tip Example – Registering a simple command

We want to register the command `/randomnumber` with the following syntax:

```mccmd
/randomnumber <min> <max>
```

To accomplish that, we register the command like this:

:::tabs
===Java
<<< @/../reference-code/src/main/java/velocity/Intro.java#registerCommandExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/velocity/Intro.kt#registerCommandExample
:::

::::