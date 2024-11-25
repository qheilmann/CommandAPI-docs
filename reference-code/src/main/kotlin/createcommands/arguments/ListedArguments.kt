package createcommands.arguments

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.greedyStringArgument
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerArgument
import org.bukkit.entity.Player

fun listedArguments() {
    // #region listedArgumentsExample
    CommandAPICommand("mycommand")
        .withArguments(PlayerArgument("player"))
        .withArguments(IntegerArgument("value").setListed(false))
        .withArguments(GreedyStringArgument("message"))
        .executes(CommandExecutor { _, args ->
            // args == [player, message]
            val player = args["player"] as Player
            val message = args["message"] as String // Note that the IntegerArgument is not available in the CommandArguments
            player.sendMessage(message)
        })
        .register()
    // #endregion listedArgumentsExample
}

fun listedArgumentsDSL() {
    // #region listedArgumentsExampleDSL
    commandAPICommand("mycommand") {
        playerArgument("player")
        integerArgument("value") { isListed = false }
        greedyStringArgument("message")
        anyExecutor { _, args ->
            // args == [player, message]
            val player = args["player"] as Player
            val message = args["message"] as String // Note that the IntegerArgument is not available in the CommandArguments
            player.sendMessage(message)
        }
    }
    // #endregion listedArgumentsExampleDSL
}