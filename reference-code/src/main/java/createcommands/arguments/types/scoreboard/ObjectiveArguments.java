package createcommands.arguments.types.scoreboard;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ObjectiveArgument;
import dev.jorel.commandapi.arguments.ObjectiveCriteriaArgument;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.Set;

class ObjectiveArguments {
    {
        // #region objectiveArgumentsExample
        new CommandAPICommand("sidebar")
            .withArguments(new ObjectiveArgument("objective"))
            .executes((sender, args) -> {
                Objective objective = (Objective) args.get("objective");

                // Set display slot
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            })
            .register();
        // #endregion objectiveArgumentsExample

        // #region objectiveCriteriaArgumentsExample
        new CommandAPICommand("unregisterall")
            .withArguments(new ObjectiveCriteriaArgument("objective criteria"))
            .executes((sender, args) -> {
                String objectiveCriteria = (String) args.get("objective criteria");
                Set<Objective> objectives = Bukkit.getScoreboardManager().getMainScoreboard().getObjectivesByCriteria(objectiveCriteria);

                // Unregister the objectives
                for (Objective objective : objectives) {
                    objective.unregister();
                }
            })
            .register();
        // #endregion objectiveCriteriaArgumentsExample
    }
}
