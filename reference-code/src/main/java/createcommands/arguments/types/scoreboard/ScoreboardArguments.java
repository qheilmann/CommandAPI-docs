package createcommands.arguments.types.scoreboard;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ScoreHolderArgument;
import dev.jorel.commandapi.arguments.ScoreboardSlotArgument;
import dev.jorel.commandapi.wrappers.ScoreboardSlot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;

class ScoreboardArguments {
    {
        // #region scoreHolderArgumentExample
        new CommandAPICommand("reward")
            // We want multiple players, so we use ScoreHolderType.MULTIPLE in the constructor
            .withArguments(new ScoreHolderArgument.Multiple("players"))
            .executes((sender, args) -> {
                // Get player names by casting to Collection<String>
                @SuppressWarnings("unchecked")
                Collection<String> players = (Collection<String>) args.get("players");

                for (String playerName : players) {
                    Bukkit.getPlayer(playerName).getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
                }
            })
            .register();
        // #endregion scoreHolderArgumentExample

        // #region scoreboardSlotArgumentExample
        new CommandAPICommand("clearobjectives")
            .withArguments(new ScoreboardSlotArgument("slot"))
            .executes((sender, args) -> {
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                DisplaySlot slot = ((ScoreboardSlot) args.get("slot")).getDisplaySlot();
                scoreboard.clearSlot(slot);
            })
            .register();
        // #endregion scoreboardSlotArgumentExample
    }
}
