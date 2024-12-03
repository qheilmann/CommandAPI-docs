package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.ListArgumentBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

class ListArguments {
    {
        // #region listArgumentsExample
        new CommandAPICommand("multigive")
            .withArguments(new IntegerArgument("amount", 1, 64))
            .withArguments(new ListArgumentBuilder<Material>("materials")
                .withList(List.of(Material.values()))
                .withMapper(material -> material.name().toLowerCase())
                .buildGreedy()
            )
            .executesPlayer((player, args) -> {
                int amount = (int) args.get("amount");
                List<Material> theList = (List<Material>) args.get("materials");

                for (Material item : theList) {
                    player.getInventory().addItem(new ItemStack(item, amount));
                }
            })
            .register();
        // #endregion listArgumentsExample
    }
}
