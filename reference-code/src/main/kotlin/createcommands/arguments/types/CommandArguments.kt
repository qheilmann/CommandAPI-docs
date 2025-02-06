package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CommandArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.SuggestionsBranch
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.commandArgument
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.wrappers.CommandResult
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun commandArguments() {
    // #region sudoCommandExample
    CommandAPICommand("sudo")
        .withArguments(PlayerArgument("target"))
        .withArguments(CommandArgument("command"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val target = args["target"] as Player
            val command = args["command"] as CommandResult

            command.execute(target)
        })
        .register()
    // #endregion sudoCommandExample

    // #region suggestionBranchesStep1
    SuggestionsBranch.suggest<CommandSender>(
        ArgumentSuggestions.strings("tp"),
        ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() },
        ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() }
    )
    // #endregion suggestionBranchesStep1

    // #region suggestionBranchesStep2
    SuggestionsBranch.suggest<CommandSender>(
        ArgumentSuggestions.strings("give"),
        ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() }
    ).branch(
        SuggestionsBranch.suggest(
            ArgumentSuggestions.strings("diamond", "minecraft:diamond"),
            ArgumentSuggestions.empty()
        ),
        SuggestionsBranch.suggest(
            ArgumentSuggestions.strings("dirt", "minecraft:dirt"),
            null,
            ArgumentSuggestions.empty()
        )
    )
    // #endregion suggestionBranchesStep2

    // #region suggestionBranchesStep3
    CommandArgument("command")
        .branchSuggestions(
            SuggestionsBranch.suggest<CommandSender>(
                ArgumentSuggestions.strings("give"),
                ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() }
            ).branch(
                SuggestionsBranch.suggest(
                    ArgumentSuggestions.strings("diamond", "minecraft:diamond"),
                    ArgumentSuggestions.empty()
                ),
                SuggestionsBranch.suggest(
                    ArgumentSuggestions.strings("dirt", "minecraft:dirt"),
                    null,
                    ArgumentSuggestions.empty()
                )
            ),
            SuggestionsBranch.suggest(
                ArgumentSuggestions.strings("tp"),
                ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() },
                ArgumentSuggestions.strings { _ -> Bukkit.getOnlinePlayers().map{ it.name }.toTypedArray() }
            )
        )
    // #endregion suggestionBranchesStep3
}

fun commandArgumentsDSL() {
    // #region sudoCommandExampleDSL
    commandAPICommand("sudo") {
        playerArgument("target")
        commandArgument("command")
        anyExecutor { _, args ->
            val target = args["target"] as Player
            val command = args["command"] as CommandResult

            command.execute(target)
        }
    }
    // #endregion sudoCommandExampleDSL
}