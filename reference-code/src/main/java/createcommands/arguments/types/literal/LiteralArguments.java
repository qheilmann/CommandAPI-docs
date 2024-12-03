package createcommands.arguments.types.literal;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import org.bukkit.GameMode;

import java.util.HashMap;
import java.util.Map;

class LiteralArguments {
    {
        // #region showLiteralArgumentsIsNotListed
        new CommandAPICommand("mycommand")
            .withArguments(new LiteralArgument("hello"))
            .withArguments(new TextArgument("text"))
            .executes((sender, args) -> {
                // This gives the variable "text" the contents of the TextArgument, and not the literal "hello"
                String text = (String) args.get(0);
            })
            .register();
        // #endregion showLiteralArgumentsIsNotListed

        // #region helperMethodsExample
        new CommandAPICommand("mycommand")
            .withArguments(LiteralArgument.of("hello"))
            .withArguments(new TextArgument("text"))
            .executes((sender, args) -> {
                // This gives the variable "text" the contents of the TextArgument, and not the literal "hello"
                String text = (String) args.get(0);
            })
            .register();

        new CommandAPICommand("mycommand")
            .withArguments(LiteralArgument.literal("hello"))
            .withArguments(new TextArgument("text"))
            .executes((sender, args) -> {
                // This gives the variable "text" the contents of the TextArgument, and not the literal "hello"
                String text = (String) args.get(0);
            })
            .register();
        // #endregion helperMethodsExample

        // #region literalArgumentsExample
        // Create a map of gamemode names to their respective objects
        HashMap<String, GameMode> gamemodes = new HashMap<>();
        gamemodes.put("adventure", GameMode.ADVENTURE);
        gamemodes.put("creative", GameMode.CREATIVE);
        gamemodes.put("spectator", GameMode.SPECTATOR);
        gamemodes.put("survival", GameMode.SURVIVAL);

        // Iterate over the map
        for (Map.Entry<String, GameMode> entry : gamemodes.entrySet()) {
            // Register the command as usual
            new CommandAPICommand("changegamemode")
                .withArguments(new LiteralArgument(entry.getKey()))
                .executesPlayer((player, args) -> {
                    // Retrieve the object from the map via the key and NOT the args[]
                    player.setGameMode(entry.getValue());
                })
                .register();
        }
        // #endregion literalArgumentsExample
    }
}
