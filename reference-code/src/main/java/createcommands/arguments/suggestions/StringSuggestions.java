package createcommands.arguments.suggestions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class StringSuggestions {
    static {
        // #region createWarpCommand
        Map<String, Location> warps = new HashMap<>();
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new StringArgument("world").replaceSuggestions(
            ArgumentSuggestions.strings(
                "northland", "eastland", "southland", "westland"
            ))
        );

        new CommandAPICommand("warp")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {
                String warp = (String) args.get("world");
                player.teleport(warps.get(warp)); // Look up the warp in a map, for example
            })
            .register();
        // #endregion createWarpCommand
    }

    // #region createFriendsClass
    class Friends {
        static Map<UUID, String[]> friendsMap = new HashMap<>();

        public static String[] getFriends(CommandSender sender) {
            if (sender instanceof Player player) {
                // Look up friends in a database or file
                return friendsMap.get(player.getUniqueId());
            } else {
                return new String[0];
            }
        }
    }
    // #endregion createFriendsClass

    {
        // #region createFriendCommand
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new PlayerArgument("friend").replaceSuggestions(
            ArgumentSuggestions.strings(info ->
                Friends.getFriends(info.sender())
            ))
        );

        new CommandAPICommand("friendtp")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("friend");
                player.teleport(target);
            })
            .register();
        // #endregion createFriendCommand

        // #region createLocalMessageCommand
        // Declare our arguments as normal
        List<Argument<?>> commandArgs = new ArrayList<>();
        commandArgs.add(new IntegerArgument("radius"));

        // Replace the suggestions for the PlayerArgument.
        // info.sender() refers to the command sender running this command
        // info.previousArgs() refers to the Object[] of previously declared arguments (in this case, the IntegerArgument radius)
        commandArgs.add(new PlayerArgument("target").replaceSuggestions(
            ArgumentSuggestions.strings(info -> {

                // Cast the first argument (radius, which is an IntegerArgument) to get its value
                int radius = (int) info.previousArgs().get("radius");

                // Get nearby entities within the provided radius
                Player player = (Player) info.sender();
                Collection<Entity> entities = player.getWorld().getNearbyEntities(
                    player.getLocation(),
                    radius,
                    radius,
                    radius
                );

                // Get player names within that radius
                return entities.stream()
                    .filter(e -> e.getType() == EntityType.PLAYER)
                    .map(Entity::getName)
                    .toArray(String[]::new);
            }))
        );
        commandArgs.add(new GreedyStringArgument("message"));

        // Declare our command as normal
        new CommandAPICommand("localmsg")
            .withArguments(commandArgs)
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");
                String message = (String) args.get("message");
                target.sendMessage(message);
            })
            .register();
        // #endregion createLocalMessageCommand
    }
}
