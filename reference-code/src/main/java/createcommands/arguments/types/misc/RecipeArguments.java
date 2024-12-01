package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.RecipeArgument;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ComplexRecipe;

class RecipeArguments {
    {
        // #region getResultExample
        new CommandAPICommand("giverecipe")
            .withArguments(new RecipeArgument("recipe"))
            .executesPlayer((player, args) -> {
                ComplexRecipe recipe = (ComplexRecipe) args.get("recipe");
                player.getInventory().addItem(recipe.getResult());
            })
            .register();
        // #endregion getResultExample

        // #region getKeyExample
        new CommandAPICommand("unlockrecipe")
            .withArguments(new PlayerArgument("player"))
            .withArguments(new RecipeArgument("recipe"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("player");
                ComplexRecipe recipe = (ComplexRecipe) args.get("recipe");

                target.discoverRecipe(recipe.getKey());
            })
            .register();
        // #endregion getKeyExample
    }
}
