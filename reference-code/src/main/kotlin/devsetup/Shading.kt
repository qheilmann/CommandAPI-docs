package devsetup

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.NormalExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

fun shading() {
    val plugin: JavaPlugin? = null
    // #region bukkitConfigExample
    CommandAPI.onLoad(CommandAPIBukkitConfig(plugin).silentLogs(true))
    // #endregion bukkitConfigExample
}

// #region shadingExample
class MyPlugin : JavaPlugin() {
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(true)) // Load with verbose output

        CommandAPICommand("ping")
            .executes(NormalExecutor<CommandSender, Any> { sender, _ ->
                sender.sendMessage("pong!")
            })
            .register()
    }

    override fun onEnable() {
        CommandAPI.onEnable()
        // Register commands, listeners etc.
    }

    override fun onDisable() {
        CommandAPI.onDisable()
    }
}
// #endregion shadingExample