package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.MapArgumentBuilder
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.argument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Bukkit
import org.bukkit.entity.Player

fun mapArguments() {
    // #region mapArgumentsExample
    CommandAPICommand("sendmessage")
        // Parameter 'delimiter' is missing, delimiter will be a colon
        // Parameter 'separator' is missing, separator will be a space
        .withArguments(MapArgumentBuilder<Player, String>("message")
            // Providing a key mapper to convert a String into a Player
            .withKeyMapper { s: String -> Bukkit.getPlayer(s) }

            // Providing a value mapper to leave the message how it was sent
            .withValueMapper { s: String -> s }

            // Providing a list of player names to be used as keys
            .withKeyList(Bukkit.getOnlinePlayers().map { player: Player -> player.name }.toList())

            // Don't provide a list of values so messages can be chosen without restrictions
            // Allow duplicates in case the same message should be sent to different players
            .withoutValueList(true)

            // Build the MapArgument
            .build()
        )
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            // The MapArgument returns a LinkedHashMap
            val map: LinkedHashMap<Player, String> = args["message"] as LinkedHashMap<Player, String>

            // Sending the messages to the players
            for (messageRecipient in map.keys) {
                messageRecipient.sendMessage(map[messageRecipient]!!)
            }
        })
        .register()
    // #endregion mapArgumentsExample
}

fun mapArgumentsDSL() {
    // #region mapArgumentsExampleDSL
    commandAPICommand("sendmessage") {
        // Parameter 'delimiter' is missing, delimiter will be a colon
        // Parameter 'separator' is missing, separator will be a space
        argument(MapArgumentBuilder<Player, String>("message")
            // Providing a key mapper to convert a String into a Player
            .withKeyMapper { s: String -> Bukkit.getPlayer(s) }

            // Providing a value mapper to leave the message how it was sent
            .withValueMapper { s: String -> s }

            // Providing a list of player names to be used as keys
            .withKeyList(Bukkit.getOnlinePlayers().map { player: Player -> player.name }.toList())

            // Don't provide a list of values so messages can be chosen without restrictions
            // Allow duplicates in case the same message should be sent to different players
            .withoutValueList(true)

            // Build the MapArgument
            .build()
        )
        playerExecutor { _, args ->
            // The MapArgument returns a LinkedHashMap
            val map: LinkedHashMap<Player, String> = args["message"] as LinkedHashMap<Player, String>

            // Sending the messages to the players
            for (messageRecipient in map.keys) {
                messageRecipient.sendMessage(map[messageRecipient]!!)
            }
        }
    }
    // #endregion mapArgumentsExampleDSL
}