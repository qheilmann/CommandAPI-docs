package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerRangeArgument;
import dev.jorel.commandapi.arguments.ItemStackArgument;
import dev.jorel.commandapi.wrappers.IntegerRange;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

class RangedArguments {
    static {
        // #region rangedArgumentsExample
        new CommandAPICommand("searchchests")
            .withArguments(new IntegerRangeArgument("range")) // Range argument
            .withArguments(new ItemStackArgument("item"))     // The item to search for
            .executesPlayer((player, args) -> {
                // Retrieve the range from the arguments
                IntegerRange range = (IntegerRange) args.get("range");
                ItemStack itemStack = (ItemStack) args.get("item");

                // Store the locations of chests with certain items
                List<Location> locations = new ArrayList<>();

                // Iterate through all chunks, and then all tile entities within each chunk
                for (Chunk chunk : player.getWorld().getLoadedChunks()) {
                    for (BlockState blockState : chunk.getTileEntities()) {

                        // The distance between the block and the player
                        int distance = (int) blockState.getLocation().distance(player.getLocation());

                        // Check if the distance is within the specified range
                        if (range.isInRange(distance)) {

                            // Check if the tile entity is a chest
                            if (blockState instanceof Chest chest) {

                                // Check if the chest contains the item specified by the player
                                if (chest.getInventory().contains(itemStack.getType())) {
                                    locations.add(chest.getLocation());
                                }
                            }
                        }

                    }
                }

                // Output the locations of the chests, or whether no chests were found
                if (locations.isEmpty()) {
                    player.sendMessage("No chests were found");
                } else {
                    player.sendMessage("Found " + locations.size() + " chests:");
                    locations.forEach(location -> {
                        player.sendMessage("  Found at: "
                            + location.getX() + ", "
                            + location.getY() + ", "
                            + location.getZ());
                    });
                }
            })
            .register();
        // #endregion rangedArgumentsExample
    }
}
