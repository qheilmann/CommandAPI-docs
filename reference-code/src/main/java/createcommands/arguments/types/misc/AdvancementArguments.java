package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.AdvancementArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

class AdvancementArguments {
    static {
        // #region advancementArgumentsExample
        new CommandAPICommand("award")
            .withArguments(new PlayerArgument("player"))
            .withArguments(new AdvancementArgument("advancement"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("player");
                Advancement advancement = (Advancement) args.get("advancement");

                // Award all criteria for the advancement
                AdvancementProgress progress = target.getAdvancementProgress(advancement);
                for (String criteria : advancement.getCriteria()) {
                    progress.awardCriteria(criteria);
                }
            })
            .register();
        // #endregion advancementArgumentsExample
    }
}
