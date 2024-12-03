package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CommandArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.SuggestionsBranch;
import dev.jorel.commandapi.wrappers.CommandResult;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class CommandArguments {
    {
        // #region sudoCommandExample
        new CommandAPICommand("sudo")
            .withArguments(new PlayerArgument("target"))
            .withArguments(new CommandArgument("command"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("target");
                CommandResult command = (CommandResult) args.get("command");

                command.execute(target);
            })
            .register();
        // #endregion sudoCommandExample

        // #region suggestionBranchesStep1
        SuggestionsBranch.suggest(
            ArgumentSuggestions.strings("tp"),
            ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new)),
            ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new))
        )
        // #endregion suggestionBranchesStep1
        ;

        // #region suggestionBranchesStep2
        SuggestionsBranch.suggest(
            ArgumentSuggestions.strings("give"),
            ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new))
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
        ;

        // #region suggestionBranchesStep3
        new CommandArgument("command")
            .branchSuggestions(
                SuggestionsBranch.<CommandSender>suggest(
                    ArgumentSuggestions.strings("give"),
                    ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new))
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
                    ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new)),
                    ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new))
                )
            );
        // #endregion suggestionBranchesStep3
    }
}
