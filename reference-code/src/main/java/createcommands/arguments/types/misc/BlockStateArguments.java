package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BlockStateArgument;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

class BlockStateArguments {
    {
        // #region blockStateArgumentsExample
        new CommandAPICommand("set")
            .withArguments(new BlockStateArgument("block"))
            .executesPlayer((player, args) -> {
                BlockData blockdata = (BlockData) args.get("block");
                Block targetBlock = player.getTargetBlockExact(256);

                // Set the block, along with its data
                targetBlock.setType(blockdata.getMaterial());
                targetBlock.getState().setBlockData(blockdata);
            })
            .register();
        // #endregion blockStateArgumentsExample
    }
}
