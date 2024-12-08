package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.TimeArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class TimeArguments {
    static {
        // #region timeArgumentsExample
        new CommandAPICommand("bigmsg")
            .withArguments(new TimeArgument("duration"))
            .withArguments(new GreedyStringArgument("message"))
            .executes((sender, args) -> {
                // Duration in ticks
                int duration = (int) args.get("duration");
                String message = (String) args.get("message");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    // Display the message to all players, with the default fade in/out times (10 and 20).
                    player.sendTitle(message, "", 10, duration, 20);
                }
            })
            .register();
        // #endregion timeArgumentsExample
    }
}
