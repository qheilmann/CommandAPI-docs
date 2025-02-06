package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.BlockStateArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.blockStateArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player

fun blockStateArguments() {
    // #region blockStateArgumentsExample
    CommandAPICommand("set")
        .withArguments(BlockStateArgument("block"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val blockdata = args["block"] as BlockData
            val targetBlock = player.getTargetBlockExact(256)

            // Set the block, along with its data
            targetBlock?.type = blockdata.material
            targetBlock?.state?.blockData = blockdata
        })
        .register()
    // #endregion blockStateArgumentsExample
}

fun blockStateArgumentsDSL() {
    // #region blockStateArgumentsExampleDSL
    commandAPICommand("set") {
        blockStateArgument("block")
        playerExecutor { player, args ->
            val blockdata = args["block"] as BlockData
            val targetBlock = player.getTargetBlockExact(256)

            // Set the block, along with its data
            targetBlock?.type = blockdata.material
            targetBlock?.state?.blockData = blockdata
        }
    }
    // #endregion blockStateArgumentsExampleDSL
}