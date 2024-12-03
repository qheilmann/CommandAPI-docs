package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.CustomArgument.CustomArgumentException;
import dev.jorel.commandapi.arguments.CustomArgument.MessageBuilder;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.World;

class CustomArguments {
    // #region declareCustomArgumentsExample
    // Function that returns our custom argument
    public Argument<World> customWorldArgument(String nodeName) {
        // Construct our CustomArgument that takes in a String input and returns a World object
        return new CustomArgument<World, String>(new StringArgument(nodeName), info -> {
            // Parse the world from our input
            World world = Bukkit.getWorld(info.input());

            if (world == null) {
                throw CustomArgumentException.fromMessageBuilder(new MessageBuilder("Unknown world: ").appendArgInput());
            } else {
                return world;
            }
        }).replaceSuggestions(ArgumentSuggestions.strings(info ->
            // List of world names on the server
            Bukkit.getWorlds().stream().map(World::getName).toArray(String[]::new))
        );
    }
    // #endregion declareCustomArgumentsExample

    {
        // #region useCustomArgumentsExample
        new CommandAPICommand("tpworld")
            .withArguments(customWorldArgument("world"))
            .executesPlayer((player, args) -> {
                player.teleport(((World) args.get("world")).getSpawnLocation());
            })
            .register();
        // #endregion useCustomArgumentsExample
    }
}
