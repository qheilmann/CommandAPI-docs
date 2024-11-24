package createcommands.executors;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Location;

class NativeSender {
    {
        // #region breakCommandExample
        new CommandAPICommand("break")
            .executesNative((sender, args) -> {
                Location location = sender.getLocation();
                if (location != null) {
                    location.getBlock().breakNaturally();
                }
            })
            .register();
        // #endregion breakCommandExample
    }
}
