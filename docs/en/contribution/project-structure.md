---
order: 2
authors:
  - JorelAli
---

# Project Structure

The CommandAPI is a relatively large project (especially from the standpoint of one guy, because the CommandAPI was written by just one guy in their spare time!) and trying to figure out what everything does is a nightmare without any guidance. I've always felt that other community project structures aren't well documented and contributing to them can be daunting. Here's the CommandAPI's project structure for you!

## CommandAPI submodule folders

This is where all the code is for the CommandAPI. The CommandAPI is a Maven project with multiple modules which each serves a different purpose:

- `commandapi-preprocessor` - The CommandAPI uses a bit of reflection to perform things which couldn’t normally be done (for example, allowing custom commands in datapacks). Reflection is inherently unsafe and can lead to runtime errors if specific fields or methods aren’t present. The CommandAPI preprocessor project is a source annotation processor that checks all declared reflection calls and looks up at compile-time whether those calls are possible - if not, it prevents the CommandAPI from building. In short, it's a compile-time reflection checker.

- `commandapi-x.x.x` - The CommandAPI needs to access various NMS methods to operate. These are implemented for the specific version given by `x.x.x`. For example, to support Minecraft `1.16.5`, the project is `commandapi-1.16.5`. The `NMS` class implementation is done in these version-specific files.

- `commandapi-core` - The main brains of the CommandAPI. This includes both the code that makes the CommandAPI run, and the API which developers can use.

- `commandapi-vh` - The CommandAPI version handler. This is a super tiny project that simply links up all the NMS version-specific files into the CommandAPI. This is only used for the actual running of the CommandAPI (e.g., the CommandAPI plugin or shading the CommandAPI). This ensures proper compile-time safety of NMS implementations.

- `commandapi-plugin` - It's the CommandAPI plugin! This is the project used for releases to both GitHub and Spigot. It's the CommandAPI all in one neat package, with a few extra features such as config-based command conversion for server owners (or other non-developers)

- `commandapi-shade` - It's the CommandAPI, but in shade-able format. It has none of the features of the CommandAPI plugin variant and can be shaded into your own plugins. Effectively, it's `commandapi-core` + `commandapi-vh` with all of the `commandapi-x.x.x` NMS implementations included.

- `commandapi-annotations` - The CommandAPI annotations project is a small compile-time annotation processor that writes CommandAPI code for you. Using a compile-time annotation processor makes the server run so much faster than using a runtime-annotation processor, because annotation processing requires reflection to inspect class metadata.
