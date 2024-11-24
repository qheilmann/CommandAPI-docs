package createcommands.executors;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

class ResultingExecutors {
    {
        // #region randomResultCommandExample
        new CommandAPICommand("randnum")
            .executes((sender, args) -> {
                return ThreadLocalRandom.current().nextInt();
            })
            .register();
        // #endregion randomResultCommandExample

        // #region randomNumberCommandExample
        // Register random number generator command from 1 to 99 (inclusive)
        new CommandAPICommand("randomnumber")
            .executes((sender, args) -> {
                return ThreadLocalRandom.current().nextInt(1, 100); // Returns random number from 1 <= x < 100
            })
            .register();
        // #endregion randomNumberCommandExample

        // #region giverewardCommandExample
        // Register reward giving system for a target player
        new CommandAPICommand("givereward")
            .withArguments(new EntitySelectorArgument.OnePlayer("target"))
            .executes((sender, args) -> {
                Player player = (Player) args.get("target");
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
                Bukkit.broadcastMessage(player.getName() + " won a rare 64 diamonds from a loot box!");
            })
            .register();
        // #endregion giverewardCommandExample
    }
}
