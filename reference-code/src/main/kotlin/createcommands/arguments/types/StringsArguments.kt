package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.greedyStringArgument
import dev.jorel.commandapi.kotlindsl.playerArgument
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun stringsArguments() {
    // #region greedyStringArgumentsExample
    CommandAPICommand("message")
        .withArguments(PlayerArgument("target"))
        .withArguments(GreedyStringArgument("message"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            (args["target"] as Player).sendMessage(args["message"] as String)
        })
        .register()
    // #endregion greedyStringArgumentsExample
}

fun stringsArgumentsDSL() {
    // #region greedyStringArgumentsExampleDSL
    commandAPICommand("message") {
        playerArgument("target")
        greedyStringArgument("message")
        anyExecutor { _, args ->
            (args["target"] as Player).sendMessage(args["message"] as String)
        }
    }
    // #endregion greedyStringArgumentsExampleDSL
}