package createcommands.arguments.types.predicate;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.BlockPredicateArgument;
import dev.jorel.commandapi.arguments.BlockStateArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.function.Predicate;

class BlockPredicateArguments {
    {
        // #region useBlockStateArgumentsExample
        Argument<?>[] arguments = new Argument<?>[] {
            new IntegerArgument("radius"),
            new BlockPredicateArgument("fromBlock"),
            new BlockStateArgument("toBlock"),
        };
        // #endregion useBlockStateArgumentsExample

        // #region blockPredicateArgumentsExample
        new CommandAPICommand("replace")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {
                // Parse the arguments
                int radius = (int) args.get("radius");
                @SuppressWarnings("unchecked")
                Predicate<Block> predicate = (Predicate<Block>) args.get("fromBlock");
                BlockData blockData = (BlockData) args.get("toBlock");

                // Find a (solid) sphere of blocks around the player with a given radius
                Location center = player.getLocation();
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        for (int z = -radius; z <= radius; z++) {
                            if (Math.sqrt(((x * x) + (y * y) + (z * z))) <= radius) {
                                Block block = center.getWorld().getBlockAt(x + center.getBlockX(), y + center.getBlockY(), z + center.getBlockZ());

                                // If that block matches a block from the predicate, set it
                                if (predicate.test(block)) {
                                    block.setType(blockData.getMaterial());
                                    block.setBlockData(blockData);
                                }
                            }
                        }
                    }
                }
            })
            .register();
        // #endregion blockPredicateArgumentsExample
    }
}
