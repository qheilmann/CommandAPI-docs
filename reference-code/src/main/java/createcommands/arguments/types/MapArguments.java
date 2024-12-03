package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.MapArgumentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class MapArguments {
    {
        // #region mapArgumentsExample
        new CommandAPICommand("sendmessage")
            // Parameter 'delimiter' is missing, delimiter will be a colon
            // Parameter 'separator' is missing, separator will be a space
            .withArguments(new MapArgumentBuilder<Player, String>("message")
                // Providing a key mapper to convert a String into a Player
                .withKeyMapper(Bukkit::getPlayer)

                // Providing a value mapper to leave the message how it was sent
                .withValueMapper(s -> s)

                // Providing a list of player names to be used as keys
                .withKeyList(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList())

                // Don't provide a list of values so messages can be chosen without restrictions
                // Allow duplicates in case the same message should be sent to different players
                .withoutValueList(true)

                // Build the MapArgument
                .build()
            )
            .executesPlayer((player, args) -> {
                // The MapArgument returns a LinkedHashMap
                LinkedHashMap<Player, String> map = (LinkedHashMap<Player, String>) args.get("message");

                // Sending the messages to the players
                for (Entry<Player, String> messageRecipients : map.entrySet()) {
                    messageRecipients.getKey().sendMessage(messageRecipients.getValue());
                }
            })
            .register();
        // #endregion mapArgumentsExample
    }
}
