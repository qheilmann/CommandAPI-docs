package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ItemStackArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.itemStackArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun itemStackArguments() {
    // #region itemStackArgumentsExample
    CommandAPICommand("item")
        .withArguments(ItemStackArgument("itemStack"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
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