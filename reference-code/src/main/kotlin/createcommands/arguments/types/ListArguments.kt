package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.ListArgumentBuilder
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.argument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun listArguments() {
    // #region listArgumentsExample
    CommandAPICommand("multigive")
        .withArguments(IntegerArgument("amount", 1, 64))
        .withArguments(ListArgumentBuilder<Material>("materials")
            .withList(Material.entries)
            .withMapper { material -> material.name.lowercase() }
            .buildGreedy()
        )
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val amount = args["amount"] as Int
            val theList = args["materials"] as List<Material>

            for (item in theList) {
                player.inventory.addItem(ItemStack(item, amount))
            }
        })
        .register()
    // #endregion listArgumentsExample
}

fun listArgumentsDSL() {
    // #region listArgumentsExampleDSL
    commandAPICommand("multigive") {
        integerArgument("amount", 1, 64)
        argument(ListArgumentBuilder<Material>("materials")
            .withList(Material.entries)
            .withMapper { material -> material.name.lowercase() }
            .buildGreedy()
        )
        playerExecutor { player, args ->
            val amount = args["amount"] as Int
            val theList = args["materials"] as List<Material>

            for (item in theList) {
                player.inventory.addItem(ItemStack(item, amount))
            }
        }
    }
    // #endregion listArgumentsExampleDSL
}