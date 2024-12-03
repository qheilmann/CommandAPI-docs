package createcommands.functionsandtags

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

// #region functionsExample
class Main : JavaPlugin() {
    override fun onLoad() {
        // Commands which will be used in Minecraft functions are registered here

        CommandAPICommand("killall")
            .executes(CommandExecutor { _, _ ->
                // Kills all enemies in all worlds
                Bukkit.getWorlds().forEach { world -> world.livingEntities.forEach { entity -> entity.health = 0.0 } }
            })
            .register()
    }

    override fun onEnable() {
        // Register all other commands here
    }
}
// #endregion functionsExample