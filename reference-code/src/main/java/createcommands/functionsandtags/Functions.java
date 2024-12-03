package createcommands.functionsandtags;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

class Functions {
    // #region functionsExample
    public class Main extends JavaPlugin {
        @Override
        public void onLoad() {
            // Commands which will be used in Minecraft functions are registered here

            new CommandAPICommand("killall")
                .executes((sender, args) -> {
                    // Kills all enemies in all worlds
                    Bukkit.getWorlds().forEach(w -> w.getLivingEntities().forEach(e -> e.setHealth(0)));
                })
                .register();
        }

        @Override
        public void onEnable() {
            // Register all other commands here
        }
    }
    // #endregion functionsExample
}
