package createcommands

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkit
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.MultiLiteralArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

fun unregistration() {
    object : JavaPlugin() {
        // #region unregisterBukkitExample
        override fun onLoad() {
            CommandAPIBukkit.unregister("version", true, true)
        }
        // #endregion unregisterBukkitExample
    }

    object : JavaPlugin() {
        // #region unregisterVanillaExample
        override fun onEnable() {
            CommandAPI.unregister("gamemode")
        }
        // #endregion unregisterVanillaExample
    }

    object : JavaPlugin() {
        // #region unregisterVanillaAndReplaceExample
        override fun onEnable() {
            CommandAPI.unregister("gamemode")

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

    object : JavaPlugin() {
        // #region unregisterPluginExample
        override fun onEnable() {
            CommandAPIBukkit.unregister("luckperms:luckperms", false, true)
        }
        // #endregion unregisterPluginExample
    }

    object : JavaPlugin() {
        // #region unregisterCommandAPIExample
        override fun onEnable() {
            CommandAPI.unregister("break")
        }
        // #endregion unregisterCommandAPIExample
    }

    object : JavaPlugin() {
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

    object : JavaPlugin() {
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

    object : JavaPlugin() {
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

    object : JavaPlugin() {
        // #region unregisterDelayedVanillaGoodExample
        override fun onEnable() {
            object : BukkitRunnable() {
                override fun run() {
                    CommandAPI.unregister("gamemode", true)
                }
            }.runTaskLater(this, 0)
        }
        // #endregion unregisterDelayedVanillaGoodExample
    }
}