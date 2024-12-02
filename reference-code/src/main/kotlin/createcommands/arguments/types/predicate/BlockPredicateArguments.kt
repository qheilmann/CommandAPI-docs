package createcommands.arguments.types.predicate

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.BlockPredicateArgument
import dev.jorel.commandapi.arguments.BlockStateArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.arguments
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import java.util.function.Predicate
import kotlin.math.sqrt

fun blockPredicateArguments() {
    // #region useBlockStateArgumentsExample
    val arguments = arrayOf<Argument<*>>(
        IntegerArgument("radius"),
        BlockPredicateArgument("fromBlock"),
        BlockStateArgument("toBlock"),
    )
    // #endregion useBlockStateArgumentsExample

    // #region blockPredicateArgumentsExample
    CommandAPICommand("replace")
        .withArguments(*arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            // Parse the arguments
            val radius = args["radius"] as Int
            val predicate = args["fromBlock"] as Predicate<Block>
            val blockData = args["toBlock"] as BlockData

            // Find a (solid) sphere of blocks around the player with a given radius
            val center = player.location // for (i in 1 until 11) { }
            for (x in -radius until radius + 1) {
                for (y in -radius until radius + 1) {
                    for (z in -radius until radius + 1) {
                        if (sqrt((x * x + y * y + z * z).toDouble()) <= radius) {
                            val block = center.world.getBlockAt(x + center.blockX, y + center.blockY, z + center.blockZ)

                            // If that block matches a block from the predicate, set it
                            if (predicate.test(block)) {
                                block.type = blockData.material
                                block.blockData = blockData
                            }
                        }
                    }
                }
            }
        })
        .register()
    // #endregion blockPredicateArgumentsExample
}

fun blockPredicateArgumentsDSL() {
    val arguments = arrayOf<Argument<*>>(
        IntegerArgument("radius"),
        BlockPredicateArgument("fromBlock"),
        BlockStateArgument("toBlock"),
    )
    // #region blockPredicateArgumentsExampleDSL
    commandAPICommand("replace") {
        arguments(*arguments)
        playerExecutor { player, args ->
            // Parse the arguments
            val radius = args["radius"] as Int
            val predicate = args["fromBlock"] as Predicate<Block>
            val blockData = args["toBlock"] as BlockData

            // Find a (solid) sphere of blocks around the player with a given radius
            val center = player.location // for (i in 1 until 11) { }
            for (x in -radius until radius + 1) {
                for (y in -radius until radius + 1) {
                    for (z in -radius until radius + 1) {
                        if (Math.sqrt((x * x + y * y + z * z).toDouble()) <= radius) {
                            val block = center.world.getBlockAt(x + center.blockX, y + center.blockY, z + center.blockZ)

                            // If that block matches a block from the predicate, set it
                            if (predicate.test(block)) {
                                block.type = blockData.material
                                block.blockData = blockData
                            }
                        }
                    }
                }
            }
        }
    }
    // #endregion blockPredicateArgumentsExampleDSL
}