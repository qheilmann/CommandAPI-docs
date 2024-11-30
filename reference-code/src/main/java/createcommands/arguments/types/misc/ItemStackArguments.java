package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ItemStackArgument;
import org.bukkit.inventory.ItemStack;

class ItemStackArguments {
    {
        // #region itemStackArgumentsExample
        new CommandAPICommand("item")
            .withArguments(new ItemStackArgument("itemStack"))
            .executesPlayer((player, args) -> {
                player.getInventory().addItem((ItemStack) args.get("itemStack"));
            })
            .register();
        // #endregion itemStackArgumentsExample
    }
}
