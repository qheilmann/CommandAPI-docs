package devsetup;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.plugin.java.JavaPlugin;

class Shading {
    {
        JavaPlugin plugin = null;
        // #region bukkitConfigExample
        CommandAPI.onLoad(new CommandAPIBukkitConfig(plugin).silentLogs(true));
        // #endregion bukkitConfigExample
    }

    // #region shadingExample
    class MyPlugin extends JavaPlugin {
        @Override
        public void onLoad() {
            CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true)); // Load with verbose output

            new CommandAPICommand("ping")
                .executes((sender, args) -> {
                    sender.sendMessage("pong!");
                })
                .register();
        }

        @Override
        public void onEnable() {
            CommandAPI.onEnable();
            // Register commands, listeners etc.
        }

        @Override
        public void onDisable() {
            CommandAPI.onDisable();
        }
    }
    // #endregion shadingExample
}
