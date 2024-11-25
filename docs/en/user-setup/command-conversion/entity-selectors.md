---
order: 4
authors:
  - misode
  - JorelAli
---

# Entity selectors

[Entity selectors](https://minecraft.wiki/w/Target_selectors) (also known as target selectors) allows you to select certain entities or players which fit a certain criteria when writing a command. Typically, these are of the form `@p`, `@r`, `@a`, `@e` or `@s`. By default, when converting a command without arguments, the CommandAPI will not handle these entity selectors. To get entity selectors to cooperate with plugins, they must be declared in the relevant `config.yml` section.

::: tip Example - Converting EssentialsX's /ext command

EssentialsX includes a command `/ext` which lets you extinguish a player that is currently on fire. The command format is the following:

```mccmd
/ext
/ext <player>
```

To convert this command, we could use the following `config.yml` file:

```yml
verbose-outputs: false
create-dispatcher-json: false
plugins-to-convert:
  - Essentials:
    - ext
```

Using the above `config.yml` file will support the following commands:

```mccmd
/ext
/ext Notch
```

However, the above `config.yml` **will not** support the following commands:

```mccmd
/ext @a[distance=10]
/ext @p
```

To handle this, we have to use the conversion with arguments (as described in the [previous section](./single-command-with-args)). For this `ext` command, we want to only use this command on _one or more players_, therefore, we want to use the `api:players` argument which is compatible with one or more players:

```yml
verbose-outputs: false
create-dispatcher-json: false
plugins-to-convert:
  - Essentials:
    - ext <player>[api:players]
    - ext
```

:::

Note that we declare `ext <player>[api:players]` _before_ we declare `ext`. This is because more precise commands MUST be declared before lesser precise commands.
