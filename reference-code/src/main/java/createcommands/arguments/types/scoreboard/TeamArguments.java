package createcommands.arguments.types.scoreboard;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.TeamArgument;
import org.bukkit.scoreboard.Team;

class TeamArguments {
    static {
        // #region teamArgumentsExample
        new CommandAPICommand("togglepvp")
            .withArguments(new TeamArgument("team"))
            .executes((sender, args) -> {
                Team team = (Team) args.get("team");

                // Toggle pvp
                team.setAllowFriendlyFire(team.allowFriendlyFire());
            })
            .register();
        // #endregion teamArgumentsExample
    }
}
