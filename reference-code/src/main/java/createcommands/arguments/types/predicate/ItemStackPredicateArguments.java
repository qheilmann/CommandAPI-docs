package createcommands.arguments.types.predicate;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ItemStackPredicateArgument;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

class ItemStackPredicateArguments {
    static {
        // #region itemStackPredicateArgumentsExample
        // Register our command
        new CommandAPICommand("rem")
            .withArguments(new ItemStackPredicateArgument("items"))
            .executesPlayer((player, args) -> {
                // Get our predicate
                @SuppressWarnings("unchecked")
                Predicate<ItemStack> predicate = (Predicate<ItemStack>) args.get("items");

                for (ItemStack item : player.getInventory()) {
                    if (predicate.test(item)) {
                        player.getInventory().remove(item);
                    }
                }
            })
            .register();
        // #endregion itemStackPredicateArgumentsExample
    }
}
