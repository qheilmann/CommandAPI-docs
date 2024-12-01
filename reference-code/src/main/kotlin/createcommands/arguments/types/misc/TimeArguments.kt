package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.arguments.TimeArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.greedyStringArgument
import dev.jorel.commandapi.kotlindsl.timeArgument
import org.bukkit.Bukkit

fun timeArguments() {
    // #region timeArgumentsExample
    CommandAPICommand("bigmsg")
        .withArguments(TimeArgument("duration"))
        .withArguments(GreedyStringArgument("message"))
        .executes(CommandExecutor { _, args ->
            // Duration in ticks
            val duration = args["duration"] as Int
            val message = args["message"] as String

            for (player in Bukkit.getOnlinePlayers()) {
                // Display the message to all players, with the default fade in/out times (10 and 20).
                player.sendTitle(message, "", 10, duration, 20)
            }
        })
        .register()
    // #endregion timeArgumentsExample
}

fun timeArgumentsDSL() {
    // #region timeArgumentsExampleDSL
    commandAPICommand("bigmsg") {
        timeArgument("duration")
        greedyStringArgument("message")
        anyExecutor { _, args ->
            // Duration in ticks
            val duration = args["duration"] as Int
            val message = args["message"] as String

            for (player in Bukkit.getOnlinePlayers()) {
                // Display the message to all players, with the default fade in/out times (10 and 20).
                player.sendTitle(message, "", 10, duration, 20)
            }
        }
    }
    // #endregion timeArgumentsExampleDSL
}