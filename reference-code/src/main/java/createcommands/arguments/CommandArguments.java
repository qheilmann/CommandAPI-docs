package createcommands.arguments;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.entity.Player;

class CommandArguments {
    static {
        // #region getArgExample
        new CommandAPICommand("mycommand")
            .withArguments(new StringArgument("name"))
            .withArguments(new IntegerArgument("amount"))
            .withOptionalArguments(new PlayerArgument("player"))
            .withOptionalArguments(new PlayerArgument("target"))
            .withOptionalArguments(new GreedyStringArgument("message"))
            .executesPlayer((player, args) -> {
                String name = (String) args.get(0); // Access arguments by index
                int amount = (int) args.get("amount"); // Access arguments by node name
                Player p = (Player) args.getOrDefault("player", player); // Access arguments using the getOrDefault(String, Object) method
                Player target = (Player) args.getOrDefault("target", () -> player); // Access arguments using the getOrDefault(String, Supplier<?>) method
                String message = (String) args.getOptional("message").orElse("Hello!"); // Access arguments using the getOptional(String) method
                // Do whatever with these values
            })
            .register();
        // #endregion getArgExample

        // #region getRawExample
        new CommandAPICommand("mycommand")
            .withArguments(new EntitySelectorArgument.ManyEntities("entities"))
            .executesPlayer((player, args) -> {
                String entitySelector = args.getRaw("entities"); // Access the raw argument with getRaw(String)
                // Do whatever with the entity selector
            })
            .register();
        // #endregion getRawExample

        // #region getUncheckedExample
        new CommandAPICommand("mycommand")
            .withArguments(new PlayerArgument("player"))
            .executesPlayer((player, args) -> {
                Player p = args.getUnchecked("player");
                // Do whatever with the player
            })
            .register();
        // #endregion getUncheckedExample

        // #region getByArgumentExample
        StringArgument nameArgument = new StringArgument("name");
        IntegerArgument amountArgument = new IntegerArgument("amount");
        PlayerArgument playerArgument = new PlayerArgument("player");
        PlayerArgument targetArgument = new PlayerArgument("target");
        GreedyStringArgument messageArgument = new GreedyStringArgument("message");

        new CommandAPICommand("mycommand")
            .withArguments(nameArgument)
            .withArguments(amountArgument)
            .withOptionalArguments(playerArgument)
            .withOptionalArguments(targetArgument)
            .withOptionalArguments(messageArgument)
            .executesPlayer((player, args) -> {
                String name = args.getByArgument(nameArgument);
                int amount = args.getByArgument(amountArgument);
                Player p = args.getByArgumentOrDefault(playerArgument, player);
                Player target = args.getByArgumentOrDefault(targetArgument, player);
                String message = args.getOptionalByArgument(messageArgument).orElse("Hello!");
                // Do whatever with these values
            })
            .register();
        // #endregion getByArgumentExample
    }
}
