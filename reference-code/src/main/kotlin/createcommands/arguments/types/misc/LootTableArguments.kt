package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.arguments.LootTableArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.locationArgument
import dev.jorel.commandapi.kotlindsl.lootTableArgument
import org.bukkit.Location
import org.bukkit.block.Container
import org.bukkit.command.CommandSender
import org.bukkit.loot.LootTable
import org.bukkit.loot.Lootable

fun lootTableArguments() {
    // #region lootTableArgumentsExample
    CommandAPICommand("giveloottable")
        .withArguments(LootTableArgument("lootTable"))
        .withArguments(LocationArgument("location", LocationType.BLOCK_POSITION))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val lootTable = args["lootTable"] as LootTable
            val location = args["location"] as Location

            val state = location.block.state

            // Check if the input block is a container (e.g., chest)
            if (state is Container && state is Lootable) {
                // Apply the loot table to the chest
                state.lootTable = lootTable
                state.update()
            }
        })
        .register()
    // #endregion lootTableArgumentsExample
}

fun lootTableArgumentsDSL() {
    // #region lootTableArgumentsExampleDSL
    commandAPICommand("giveloottable") {
        lootTableArgument("loottable")
        locationArgument("location", LocationType.BLOCK_POSITION)
        anyExecutor { _, args ->
            val lootTable = args["loottable"] as LootTable
            val location = args["location"] as Location

            val state = location.block.state

            // Check if the input block is a container (e.g. chest)
            if (state is Container && state is Lootable) {
                // Apply the loot table to the chest
                state.lootTable = lootTable
                state.update()
            }
        }
    }
    // #endregion lootTableArgumentsExampleDSL
}