package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EnchantmentArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.enchantments.Enchantment;

class EnchantmentArguments {
    static {
        // #region enchantmentArgumentsExample
        new CommandAPICommand("enchantitem")
            .withArguments(new EnchantmentArgument("enchantment"))
            .withArguments(new IntegerArgument("level", 1, 5))
            .executesPlayer((player, args) -> {
                Enchantment enchantment = (Enchantment) args.get("enchantment");
                int level = (int) args.get("level");

                // Add the enchantment
                player.getInventory().getItemInMainHand().addEnchantment(enchantment, level);
            })
            .register();
        // #endregion enchantmentArgumentsExample
    }
}
