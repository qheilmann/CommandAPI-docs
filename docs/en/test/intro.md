---
order: 1
authors:
  - willkroboth
---

# Testing Commands

When developing large projects, it is good practice to add automated tests for your code. This section of the documentation describes how to use the `commandapi-bukkit-test-toolkit` dependency along with [MockBukkit](https://github.com/MockBukkit/MockBukkit) and [JUnit](https://junit.org/junit5/) to test the usage of commands registered with the CommandAPI.

For a big-picture view, you can find example projects that include automated tests in the [CommandAPI GitHub repository](https://github.com/CommandAPI/CommandAPI/tree/master/examples).

:::danger Developer's Note:

Many methods have not yet been implemented in the test toolkit. Most notably, only [primitive arguments](../create-commands/arguments/types/primitive-arguments), [String arguments](../create-commands/arguments/types/string-arguments), [literal arguments](../create-commands/arguments/types/literal/), and the [`IntegerRangeArgument`](../create-commands/arguments/types/ranged-arguments) are fully implemented. The [`EntitySelectorArgument`, `PlayerArgument`, and `OfflinePlayerArgument`](../create-commands/arguments/types/entities-arguments) should mostly work, though [target selector arguments](https://minecraft.wiki/w/Target_selectors#Target_selector_arguments) (e.g. `@e[type=pig]`) are not yet implemented.

If a test ends up calling a method that has not yet been implemented, an `UnimplementedMethodException` will be thrown, causing the test to fail. If you see an `UnimplementedMethodException`, please tell us about it with a [GitHub Issue](https://github.com/CommandAPI/CommandAPI/issues) or a message in the CommandAPI Discord. Pull requests are also always welcome!

In the short term, you can try to resolve an `UnimplementedMethodException` by implementing the method yourself. The process for doing that is described [here](./load-mock-commandapi#loading-a-custom-commandapi-platform-implementation).

:::