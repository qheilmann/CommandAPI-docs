package createcommands

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkit
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.MultiLiteralArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

val unregisterBukkit = object : JavaPlugin() {
    // #region unregisterBukkitExample
    override fun onLoad() {
        CommandAPIBukkit.unregister("version", true, true)
    }
    // #endregion unregisterBukkitExample
}

val unregisterVanilla = object : JavaPlugin() {
    // #region unregisterVanillaExample
    override fun onEnable() {
        CommandAPI.unregister("gamemode")
    }
    // #endregion unregisterVanillaExample
}

val unregisterVanillaAndReplace = object : JavaPlugin() {
    // #region unregisterVanillaAndReplaceExample
    override fun onEnable() {
        CommandAPI.unregister("gamemode");

        // Register our new /gamemode, with survival, creative, adventure and spectator
        CommandAPICommand("gamemode")
            .withArguments(MultiLiteralArgument("gamemodes", "survival", "creative", "adventure", "spectator"))
            .executes(CommandExecutor { sender, args ->
                // Implementation of our /gamemode command
            })
            .register()
    }
    // #endregion unregisterVanillaAndReplaceExample
}

val unregisterPlugin = object : JavaPlugin() {
    // #region unregisterPluginExample
    override fun onEnable() {
        CommandAPIBukkit.unregister("luckperms:luckperms", false, true)
    }
    // #endregion unregisterPluginExample
}

val unregisterCommandAPI = object : JavaPlugin() {
    // #region unregisterCommandAPIExample
    override fun onEnable() {
        CommandAPI.unregister("break")
    }
    // #endregion unregisterCommandAPIExample
}

val unregisterBukkitHelp = object : JavaPlugin() {
    // #region unregisterBukkitHelpExample
    override fun onEnable() {
        object : BukkitRunnable() {
            override fun run() {
                CommandAPIBukkit.unregister("help", false, true)
            }
        }.runTaskLater(this, 0)
    }
    // #endregion unregisterBukkitHelpExample
}

val unregisterVanillaNamespaceOnly = object : JavaPlugin() {
    // #region unregisterVanillaNamespaceOnlyExample
    override fun onEnable() {
        object : BukkitRunnable() {
            override fun run() {
                CommandAPI.unregister("minecraft:gamemode")
            }
        }.runTaskLater(this, 0)
    }
    // #endregion unregisterVanillaNamespaceOnlyExample
}

val unregisterDelayedVanillaBad = object : JavaPlugin() {
    // #region unregisterDelayedVanillaBadExample
    // NOT RECOMMENDED
    override fun onEnable() {
        object : BukkitRunnable() {
            override fun run() {
                CommandAPI.unregister("gamemode")
            }
        }.runTaskLater(this, 0)
    }
    // #endregion unregisterDelayedVanillaBadExample
}

val unregisterDelayedVanillaGood = object : JavaPlugin() {
    // #region unregisterDelayedVanillaGoodExample
    // NOT RECOMMENDED
    override fun onEnable() {
        object : BukkitRunnable() {
            override fun run() {
                CommandAPI.unregister("gamemode", true)
            }
        }.runTaskLater(this, 0)
    }
    // #endregion unregisterDelayedVanillaGoodExample
}