package createcommands.arguments.types.scoreboard

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.TeamArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.teamArgument
import org.bukkit.scoreboard.Team

fun teamArguments() {
    // #region teamArgumentsExample
    CommandAPICommand("togglepvp")
        .withArguments(TeamArgument("team"))
        .executes(CommandExecutor { _, args ->
            val team = args["team"] as Team

            // Toggle pvp
            team.setAllowFriendlyFire(team.allowFriendlyFire())
        })
        .register()
    // #endregion teamArgumentsExample
}

fun teamArgumentsDSL() {
    // #region teamArgumentsExampleDSL
    commandAPICommand("togglepvp") {
        teamArgument("team")
        anyExecutor { _, args ->
            val team = args["team"] as Team

            // Toggle pvp
            team.setAllowFriendlyFire(team.allowFriendlyFire())
        }
    }
    // #endregion teamArgumentsExampleDSL
}