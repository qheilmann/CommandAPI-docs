package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.EnchantmentArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.enchantmentArgument
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.enchantments.Enchantment

fun enchantmentArguments() {
    // #region enchantmentArgumentsExample
    CommandAPICommand("enchantitem")
        .withArguments(EnchantmentArgument("enchantment"))
        .withArguments(IntegerArgument("level", 1, 5))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val enchantment = args["enchantment"] as Enchantment
            val level = args["level"] as Int

            // Add the enchantment
            player.inventory.itemInMainHand.addEnchantment(enchantment, level)
        })
        .register()
    // #endregion enchantmentArgumentsExample
}

fun enchantmentArgumentsDSL() {
    // #region enchantmentArgumentsExampleDSL
    commandAPICommand("enchantitem") {
        enchantmentArgument("enchantment")
        integerArgument("level", 1, 5)
        playerExecutor { player, args ->
            val enchantment = args["enchantment"] as Enchantment
            val level = args["level"] as Int

            // Add the enchantment
            player.inventory.itemInMainHand.addEnchantment(enchantment, level)
        }
    }
    // #endregion enchantmentArgumentsExampleDSL
}