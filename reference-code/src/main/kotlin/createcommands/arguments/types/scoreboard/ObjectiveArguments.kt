package createcommands.arguments.types.scoreboard

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ObjectiveArgument
import dev.jorel.commandapi.arguments.ObjectiveCriteriaArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.objectiveArgument
import dev.jorel.commandapi.kotlindsl.objectiveCriteriaArgument
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective

fun objectiveArguments() {
    // #region objectiveArgumentsExample
    CommandAPICommand("sidebar")
        .withArguments(ObjectiveArgument("objective"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val objective = args["objective"] as Objective

            // Set display slot
            objective.displaySlot = DisplaySlot.SIDEBAR
        })
        .register()
    // #endregion objectiveArgumentsExample

    // #region objectiveCriteriaArgumentsExample
    CommandAPICommand("unregisterall")
        .withArguments(ObjectiveCriteriaArgument("objective criteria"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val objectiveCriteria = args["objective criteria"] as String
            val objectives = Bukkit.getScoreboardManager().mainScoreboard.getObjectivesByCriteria(objectiveCriteria)

            // Unregister the objectives
            for (objective in objectives) {
                objective.unregister()
            }
        })
        .register()
    // #endregion objectiveCriteriaArgumentsExample
}

fun objectiveArgumentsDSL() {
    // #region objectiveArgumentsExampleDSL
    commandAPICommand("sidebar") {
        objectiveArgument("objective")
        anyExecutor { _, args ->
            val objective = args["objective"] as Objective

            // Set display slot
            objective.displaySlot = DisplaySlot.SIDEBAR
        }
    }
    // #endregion objectiveArgumentsExampleDSL

    // #region objectiveCriteriaArgumentsExampleDSL
    commandAPICommand("unregisterall") {
        objectiveCriteriaArgument("objective criteria")
        anyExecutor { _, args ->
            val objectiveCriteria = args["objective criteria"] as String
            val objectives = Bukkit.getScoreboardManager().mainScoreboard.getObjectivesByCriteria(objectiveCriteria)

            // Unregister the objectives
            for (objective in objectives) {
                objective.unregister()
            }
        }
    }
    // #endregion objectiveCriteriaArgumentsExampleDSL
}