package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ItemStackArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.itemStackArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.inventory.ItemStack

fun itemStackArguments() {
    // #region itemStackArgumentsExample
    CommandAPICommand("item")
        .withArguments(ItemStackArgument("itemStack"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            player.inventory.addItem(args["itemStack"] as ItemStack)
        })
        .register()
    // #endregion itemStackArgumentsExample
}

fun itemStackArgumentsDSL() {
    // #region itemStackArgumentsExampleDSL
    commandAPICommand("item") {
        itemStackArgument("itemstack")
        playerExecutor { player, args ->
            player.inventory.addItem(args["itemstack"] as ItemStack)
        }
    }
    // #endregion itemStackArgumentsExampleDSL
}