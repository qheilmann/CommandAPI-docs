package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerRangeArgument
import dev.jorel.commandapi.arguments.ItemStackArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.integerRangeArgument
import dev.jorel.commandapi.kotlindsl.itemStackArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.wrappers.IntegerRange
import org.bukkit.Location
import org.bukkit.block.Chest
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun rangedArguments() {
    // #region rangedArgumentsExample
    CommandAPICommand("searchchests")
        .withArguments(IntegerRangeArgument("range")) // Range argument
        .withArguments(ItemStackArgument("item"))     // The item to search for
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            // Retrieve the range from the arguments
            val range = args["range"] as IntegerRange
            val itemStack = args["item"] as ItemStack

            // Store the locations of chests with certain items
            val locations = mutableListOf<Location>()

            // Iterate through all chunks, and then all tile entities within each chunk
            for (chunk in player.world.loadedChunks) {
                for (blockState in chunk.tileEntities) {

                    // The distance between the block and the player
                    val distance = blockState.location.distance(player.location).toInt()

                    // Check if the distance is within the specified range
                    if (range.isInRange(distance)) {

                        // Check if the tile entity is a chest
                        if (blockState is Chest) {

                            // Check if the chest contains the item specified by the player
                            if (blockState.inventory.contains(itemStack.type)) {
                                locations.add(blockState.location)
                            }
                        }
                    }

                }
            }

            // Output the locations of the chests, or whether no chests were found
            if (locations.isEmpty()) {
                player.sendMessage("No chests were found")
            } else {
                player.sendMessage("Found ${locations.size} chests:")
                locations.forEach {
                    player.sendMessage("  Found at: ${it.x}, ${it.y}, ${it.z}")
                }
            }
        })
        .register()
    // #endregion rangedArgumentsExample
}

fun rangedArgumentsDSL() {
    // #region rangedArgumentsExampleDSL
    commandAPICommand("searchchests") {
        integerRangeArgument("range") // Range argument
        itemStackArgument("item") // The item to search for
        playerExecutor { player, args ->
            // Retrieve the range from the arguments
            val range = args["range"] as IntegerRange
            val itemStack = args["item"] as ItemStack

            // Store the locations of chests with certain items
            val locations = mutableListOf<Location>()

            // Iterate through all chunks, and then all tile entities within each chunk
            for (chunk in player.world.loadedChunks) {
                for (blockState in chunk.tileEntities) {

                    // The distance between the block and the player
                    val distance = blockState.location.distance(player.location).toInt()

                    // Check if the distance is within the specified range
                    if (range.isInRange(distance)) {

                        // Check if the tile entity is a chest
                        if (blockState is Chest) {

                            // Check if the chest contains the item specified by the player
                            if (blockState.inventory.contains(itemStack.type)) {
                                locations.add(blockState.location)
                            }
                        }
                    }

                }
            }

            // Output the locations of the chests, or whether no chests were found
            if (locations.isEmpty()) {
                player.sendMessage("No chests were found")
            } else {
                player.sendMessage("Found ${locations.size} chests:")
                locations.forEach {
                    player.sendMessage("  Found at: ${it.x}, ${it.y}, ${it.z}")
                }
            }
        }
    }
    // #endregion rangedArgumentsExampleDSL
}