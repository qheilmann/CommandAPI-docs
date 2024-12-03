package createcommands.arguments.types.predicate

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ItemStackPredicateArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.itemStackPredicateArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.inventory.ItemStack
import java.util.function.Predicate

fun itemStackPredicateArguments() {
    // #region itemStackPredicateArgumentsExample
    // Register our command
    CommandAPICommand("rem")
        .withArguments(ItemStackPredicateArgument("items"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            // Get our predicate
            val predicate = args["items"] as Predicate<ItemStack>

            for (item in player.inventory) {
                if (predicate.test(item)) {
                    player.inventory.remove(item)
                }
            }
        })
        .register()
    // #endregion itemStackPredicateArgumentsExample
}

fun itemStackPredicateArgumentsDSL() {
    // #region itemStackPredicateArgumentsExampleDSL
    // Register our command
    commandAPICommand("rem") {
        itemStackPredicateArgument("items")
        playerExecutor { player, args ->
            // Get our predicate
            val predicate = args["items"] as Predicate<ItemStack>

            for (item in player.inventory) {
                if (predicate.test(item)) {
                    player.inventory.remove(item)
                }
            }
        }
    }
    // #endregion itemStackPredicateArgumentsExampleDSL
}