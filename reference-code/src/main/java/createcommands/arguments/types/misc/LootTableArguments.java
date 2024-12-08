package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.LocationType;
import dev.jorel.commandapi.arguments.LootTableArgument;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

class LootTableArguments {
    static {
        // #region lootTableArgumentsExample
        new CommandAPICommand("giveloottable")
            .withArguments(new LootTableArgument("lootTable"))
            .withArguments(new LocationArgument("location", LocationType.BLOCK_POSITION))
            .executes((sender, args) -> {
                LootTable lootTable = (LootTable) args.get("lootTable");
                Location location = (Location) args.get("location");

                BlockState state = location.getBlock().getState();

                // Check if the input block is a container (e.g., chest)
                if (state instanceof Container container && state instanceof Lootable lootable) {
                    // Apply the loot table to the chest
                    lootable.setLootTable(lootTable);
                    container.update();
                }
            })
            .register();
        // #endregion lootTableArgumentsExample
    }
}
