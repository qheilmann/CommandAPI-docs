package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.MathOperationArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import dev.jorel.commandapi.wrappers.MathOperation
import org.bukkit.entity.Player

fun mathOperationArguments() {
    // #region mathOperationArgumentsExample
    CommandAPICommand("changelevel")
        .withArguments(PlayerArgument("player"))
        .withArguments(MathOperationArgument("operation"))
        .withArguments(IntegerArgument("value"))
        .executes(CommandExecutor { _, args ->
            val target = args["player"] as Player
            val op = args["operation"] as MathOperation
            val value = args["value"] as Int

            target.level = op.apply(target.level, value)
        })
        .register()
    // #endregion mathOperationArgumentsExample
}

fun mathOperationArgumentsDSL() {
    // #region mathOperationArgumentsExampleDSL
    commandAPICommand("changelevel") {
        playerArgument("player")
        mathOperationArgument("operation")
        integerArgument("value")
        anyExecutor { _, args ->
            val target = args["player"] as Player
            val op = args["operation"] as MathOperation
            val value = args["value"] as Int

            target.level = op.apply(target.level, value)
        }
    }
    // #endregion mathOperationArgumentsExampleDSL
}