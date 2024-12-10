package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.ResultingCommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.anyResultingExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

fun resultingExecutors() {
    // #region randomResultCommandExample
    CommandAPICommand("randnum")
        .executes(ResultingCommandExecutor { _, _ ->
            Random.nextInt()
        })
        .register()
    // #endregion randomResultCommandExample

    // #region randomNumberCommandExample
    // Register random number generator command from 1 to 99 (inclusive)
    CommandAPICommand("randomnumber")
        .executes(ResultingCommandExecutor { _, _ ->
            (1..100).random() // Returns random number from 1 <= x < 100
        })
        .register()
    // #endregion randomNumberCommandExample

    // #region giverewardCommandExample
    // Register reward giving system for a target player
    CommandAPICommand("givereward")
        .withArguments(EntitySelectorArgument.OnePlayer("target"))
        .executes(CommandExecutor { _, args ->
            val player = args["target"] as Player
            player.inventory.addItem(ItemStack(Material.DIAMOND, 64))
            Bukkit.broadcastMessage("${player.name} won a rare 64 diamonds from a loot box!")
        })
        .register()
    // #endregion giverewardCommandExample
}

fun resultingExecutorsDSL() {
    // #region randomResultCommandExampleDSL
    commandAPICommand("randnum") {
        anyResultingExecutor { _, _ ->
            Random.nextInt()
        }
    }
    // #endregion randomResultCommandExampleDSL

    // #region randomNumberCommandExampleDSL
    // Register random number generator command from 1 to 99 (inclusive)
    commandAPICommand("randomnumber") {
        anyResultingExecutor { _, _ ->
            (1..100).random()
        }
    }
    // #endregion randomNumberCommandExampleDSL

    // #region giverewardCommandExampleDSL
    // Register reward giving a system for a target player
    commandAPICommand("givereward") {
        entitySelectorArgumentOnePlayer("target")
        anyExecutor { _, args ->
            val player = args["target"] as Player
            player.inventory.addItem(ItemStack(Material.DIAMOND, 64))
            Bukkit.broadcastMessage("${player.name} won a rare 64 diamonds from a loot box!")
        }
    }
    // #endregion giverewardCommandExampleDSL
}