package createcommands.arguments;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

class ListedArguments {
    {
        // #region listedArgumentsExample
        new CommandAPICommand("mycommand")
            .withArguments(new PlayerArgument("player"))
            .withArguments(new IntegerArgument("value").setListed(false))
            .withArguments(new GreedyStringArgument("message"))
            .executes((sender, args) -> {
                // args == [player, message]
                Player player = (Player) args.get("player");
                String message = (String) args.get("message"); // Note that the IntegerArgument is not available in the CommandArguments
                player.sendMessage(message);
            })
            .register();
        // #endregion listedArgumentsExample
    }
}
