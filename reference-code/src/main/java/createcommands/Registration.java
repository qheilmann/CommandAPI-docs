package createcommands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;

class Registration {
    static {
        // #region registrationExample
        // Create our command
        new CommandAPICommand("broadcastmsg")
            .withArguments(new GreedyStringArgument("message")) // The arguments
            .withAliases("broadcast", "broadcastmessage")       // Command aliases
            .withPermission(CommandPermission.OP)               // Required permissions
            .executes((sender, args) -> {
                String message = (String) args.get("message");
                Bukkit.getServer().broadcastMessage(message);
            })
            .register();
        // #endregion registrationExample
    }
}
