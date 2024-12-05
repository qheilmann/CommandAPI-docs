package annotations;

import annotations.Intro.WarpCommand;
import dev.jorel.commandapi.CommandAPI;
import org.bukkit.plugin.java.JavaPlugin;

class Registration {
    // #region registerCommand
    public class MyPlugin extends JavaPlugin {
        @Override
        public void onLoad() {
            CommandAPI.registerCommand(WarpCommand.class);
        }
    }
    // #endregion registerCommand
}
