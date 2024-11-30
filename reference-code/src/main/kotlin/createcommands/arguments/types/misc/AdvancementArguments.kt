package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.AdvancementArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.advancementArgument
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import org.bukkit.advancement.Advancement
import org.bukkit.entity.Player

fun advancementArguments() {
    // #region advancementArgumentsExample
    CommandAPICommand("award")
        .withArguments(PlayerArgument("player"))
        .withArguments(AdvancementArgument("advancement"))
        .executes(CommandExecutor { _, args ->
            val target = args["player"] as Player
            val advancement = args["advancement"] as Advancement

            // Award all criteria for the advancement
            val progress = target.getAdvancementProgress(advancement)
            for (criteria in advancement.criteria) {
                progress.awardCriteria(criteria)
            }
        })
        .register()
    // #endregion advancementArgumentsExample
}

fun advancementArgumentsDSL() {
    // #region advancementArgumentsExampleDSL
    commandAPICommand("award") {
        playerArgument("player")
        advancementArgument("advancement")
        anyExecutor { _, args ->
            val target = args["player"] as Player
            val advancement = args["advancement"] as Advancement

            // Award all criteria for the advancement
            val progress = target.getAdvancementProgress(advancement)
            for (criteria in advancement.criteria) {
                progress.awardCriteria(criteria)
            }
        }
    }
    // #endregion advancementArgumentsExampleDSL
}