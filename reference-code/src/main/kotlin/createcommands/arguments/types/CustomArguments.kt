package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.CustomArgument.CustomArgumentException
import dev.jorel.commandapi.arguments.CustomArgument.MessageBuilder
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Bukkit
import org.bukkit.World

// #region declareCustomArgumentsExample
// Function that returns our custom argument
fun worldArgument(nodeName: String): Argument<World> {
    // Construct our CustomArgument that takes in a String input and returns a World object
    return CustomArgument<World, String>(StringArgument(nodeName)) { info ->
        // Parse the world from our input
        val world = Bukkit.getWorld(info.input())

        if (world == null) {
            throw CustomArgumentException.fromMessageBuilder(MessageBuilder("Unknown world: ").appendArgInput())
        } else {
            world
        }
    }.replaceSuggestions(ArgumentSuggestions.strings { _ ->
        // List of world names on the server
        Bukkit.getWorlds().map{ it.name }.toTypedArray()
    })
}
// #endregion declareCustomArgumentsExample


fun customArguments() {
    // #region useCustomArgumentsExample
    CommandAPICommand("tpworld")
        .withArguments(worldArgument("world"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            player.teleport((args["world"] as World).spawnLocation)
        })
        .register()
    // #endregion useCustomArgumentsExample
}

fun customArgumentsDSL() {
    // #region useCustomArgumentsExampleDSL
    commandAPICommand("tpworld") {
        worldArgument("world") // This method is actually also built into the Kotlin DSL
        playerExecutor { player, args ->
            player.teleport((args["world"] as World).spawnLocation)
        }
    }
    // #endregion useCustomArgumentsExampleDSL
}