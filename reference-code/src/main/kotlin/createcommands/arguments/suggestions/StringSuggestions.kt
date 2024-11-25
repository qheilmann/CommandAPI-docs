package createcommands.arguments.suggestions

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.SuggestionInfo
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.UUID

// #region createFriendsClass
object Friends {
    val friends = mutableMapOf<UUID, Array<String>>()

    fun getFriends(sender: CommandSender): Array<String> {
        return if (sender is Player) {
            // Look up friends in a database or file
            friends[sender.uniqueId]!!
        } else {
            arrayOf<String>()
        }
    }
}
// #endregion createFriendsClass

fun stringSuggestions() {
    // #region createWarpCommand
    val warps = mapOf<String, Location>()
    val arguments = listOf<Argument<*>>(
        StringArgument("world").replaceSuggestions(
            ArgumentSuggestions.strings(
                "northland", "eastland", "southland", "westland"
            )
        )
    )

    CommandAPICommand("warp")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val warp = args["world"] as String
            player.teleport(warps[warp]!!) // Look up the warp in a map, for example
        })
        .register()
    // #endregion createWarpCommand
}

fun stringSuggestions2() {
    // #region createFriendCommand
    val arguments = listOf<Argument<*>>(
        PlayerArgument("friend").replaceSuggestions(
            ArgumentSuggestions.strings { info ->
                Friends.getFriends(info.sender())
            }
        )
    )

    CommandAPICommand("friendtp")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["friend"] as Player
            player.teleport(target)
        })
        .register()
    // #endregion createFriendCommand

    // #region createLocalMessageCommand
    // Declare our arguments as normal
    val commandArgs = mutableListOf<Argument<*>>()
    commandArgs.add(IntegerArgument("radius"))

    // Replace the suggestions for the PlayerArgument.
    // info.sender() refers to the command sender running this command
    // info.previousArgs() refers to the Object[] of previously declared arguments (in this case, the IntegerArgument radius)
    commandArgs.add(PlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings { info: SuggestionInfo<CommandSender> ->

        // Cast the first argument (radius, which is an IntegerArgument) to get its value
        val radius = (info.previousArgs()["radius"] as Int).toDouble()

        // Get nearby entities within the provided radius
        val player = info.sender() as Player
        val entities = player.world.getNearbyEntities(player.location, radius, radius, radius)

        // Get player names within that radius
        entities
            .filter { it.type == EntityType.PLAYER }
            .map { it.name }
            .toTypedArray()
    }))
    commandArgs.add(GreedyStringArgument("message"))

    // Declare our command as normal
    CommandAPICommand("localmsg")
        .withArguments(*commandArgs.toTypedArray())
        .executesPlayer(PlayerCommandExecutor { _, args ->
            val target = args["target"] as Player
            val message = args["message"] as String
            target.sendMessage(message)
        })
        .register()
    // #endregion createLocalMessageCommand
}