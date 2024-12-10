package createcommands.arguments;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class OptionalArguments {
    static {
        // #region simpleOptionalArgumentsExample
        new CommandAPICommand("sayhi")
            .withOptionalArguments(new PlayerArgument("target"))
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");
                if (target != null) {
                    target.sendMessage("Hi!");
                } else {
                    player.sendMessage("Hi!");
                }
            })
            .register();
        // #endregion simpleOptionalArgumentsExample

        // #region getOptionalExample
        new CommandAPICommand("sayhi")
            .withOptionalArguments(new PlayerArgument("target"))
            .executesPlayer((player, args) -> {
                Player target = (Player) args.getOptional("target").orElse(player);
                target.sendMessage("Hi!");
            })
            .register();
        // #endregion getOptionalExample

        // #region argumentsAfterOptionalArgumentsExample
        new CommandAPICommand("rate")
            .withOptionalArguments(new StringArgument("topic").combineWith(new IntegerArgument("rating", 0, 10)))
            .withOptionalArguments(new PlayerArgument("target"))
            .executes((sender, args) -> {
                String topic = (String) args.get("topic");
                if (topic == null) {
                    sender.sendMessage(
                        "Usage: /rate <topic> <rating> <player>(optional)",
                        "Select a topic to rate, then give a rating between 0 and 10",
                        "You can optionally add a player at the end to give the rating to"
                    );
                    return;
                }

                // We know this is not null because rating is required if a topic is given
                int rating = (int) args.get("rating");

                // The target player is optional, so give it a default here
                CommandSender target = (CommandSender) args.getOptional("target").orElse(sender);

                target.sendMessage("Your " + topic + " was rated: " + rating + "/10");
            })
            .register();
        // #endregion argumentsAfterOptionalArgumentsExample
    }
}
