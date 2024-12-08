---
order: 1
authors:
  - JorelAli
  - MC-XiaoHei
---

<!--suppress HtmlUnknownAttribute, ES6UnusedImports -->
<script setup>
import PluginExtractor from '../../../.vitepress/theme/components/PluginExtractor.vue';
import ConfigValidator from '../../../.vitepress/theme/components/ConfigValidator.vue';
</script>

# Command Conversion for server admins

The CommandAPI can convert plugin commands into "Vanilla compatible" commands automatically on startup. This allows you to use `/execute` and Minecraft functions/tags for plugins that don’t use the CommandAPI. For example, if you want to use the `/hat` command from the plugin `Essentials` in an `/execute` command or from a command block, you can use the CommandAPI's command conversion setting to do so.

The CommandAPI has 3 different conversion methods, each one more specific and powerful than the others. These are the following:

- [**Entire plugin conversion**](#converting-all-plugin-commands)

  Converts all commands from a plugin into Vanilla compatible commands

- [**Single command conversion**](./single-command)

  Converts a single command into a Vanilla compatible command

- [**Single command conversion with custom arguments**](./single-command-with-args)

  Converts a single command from a plugin into a Vanilla compatible command, whilst also declaring what the arguments to the command are

## Extract plugin info

Drag a plugin here to view a list of available commands which can be registered for the CommandAPI.

<PluginExtractor></PluginExtractor>

## YAML configuration rules

To configure command conversion, the CommandAPI reads this information from the `config.yml` file. This file has a bit of a weird structure, so to put it simply, these are the following rules:

- **`config.yml` cannot have tab characters** - The `config.yml` file _must_ only consist of spaces!
- one space after the colon in key-value pairs is required.

If you're uncertain if your configuration is valid (or you're getting weird errors in the console), you can check if your configuration is valid by dropping your `config.yml` file below:

<ConfigValidator></ConfigValidator>

### Converting all plugin commands

To convert all the commands that a plugin has, add the name of the plugin, followed by a `~` character to the list of plugins to convert in the `config.yml` file.

::: tip Example – Converting all commands from EssentialsX

For example, if you wanted to convert all commands that [EssentialsX](https://www.spigotmc.org/resources/essentialsx.9089/) has, you can use the following `config.yml`:

```yaml
verbose-outputs: true
create-dispatcher-json: false
plugins-to-convert: 
  - Essentials: ~
```

:::