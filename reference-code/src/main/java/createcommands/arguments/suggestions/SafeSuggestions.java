package createcommands.arguments.suggestions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.EntityTypeArgument;
import dev.jorel.commandapi.arguments.PotionEffectArgument;
import dev.jorel.commandapi.arguments.RecipeArgument;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

class SafeSuggestions {
    static {
        JavaPlugin plugin = null;
        // #region registerCustomItem
        // Create our itemstack
        ItemStack emeraldSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = emeraldSword.getItemMeta();
        meta.setDisplayName("Emerald Sword");
        meta.setUnbreakable(true);
        emeraldSword.setItemMeta(meta);

        // Create and register our recipe
        ShapedRecipe emeraldSwordRecipe = new ShapedRecipe(new NamespacedKey(plugin, "emerald_sword"), emeraldSword);
        emeraldSwordRecipe.shape(
            "AEA",
            "AEA",
            "ABA"
        );
        emeraldSwordRecipe.setIngredient('A', Material.AIR);
        emeraldSwordRecipe.setIngredient('E', Material.EMERALD);
        emeraldSwordRecipe.setIngredient('B', Material.BLAZE_ROD);
        getServer().addRecipe(emeraldSwordRecipe);

        // Omitted, more itemstacks and recipes
        // #endregion registerCustomItem

        // #region registerCommandWithSafeSuggestions
        // Safely override with the recipe we've defined
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new RecipeArgument("recipe").replaceSafeSuggestions(dev.jorel.commandapi.arguments.SafeSuggestions.suggest(info ->
            new Recipe[]{emeraldSwordRecipe, /* Other recipes here */}
        )));

        // Register our command
        new CommandAPICommand("giverecipe")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {
                Recipe recipe = (Recipe) args.get("recipe");
                player.getInventory().addItem(recipe.getResult());
            })
            .register();
        // #endregion registerCommandWithSafeSuggestions

        // #region createForbiddenMobsList
        EntityType[] forbiddenMobs = new EntityType[]{EntityType.ENDER_DRAGON, EntityType.WITHER};
        List<EntityType> allowedMobs = new ArrayList<>(Arrays.asList(EntityType.values()));
        allowedMobs.removeAll(Arrays.asList(forbiddenMobs)); // Now contains everything except enderd ragon and wither
        // #endregion createForbiddenMobsList

        // #region createSafeArguments
        List<Argument<?>> safeArguments = new ArrayList<>();
        safeArguments.add(new EntityTypeArgument("mob").replaceSafeSuggestions(dev.jorel.commandapi.arguments.SafeSuggestions.suggest(
            info -> {
                if (info.sender().isOp()) {
                    // All entity types
                    return EntityType.values();
                } else {
                    // Only allowedMobs
                    return allowedMobs.toArray(new EntityType[0]);
                }
            })
        ));
        // #endregion createSafeArguments

        // #region registerSpawnMobCommand
        new CommandAPICommand("spawnmob")
            .withArguments(safeArguments)
            .executesPlayer((player, args) -> {
                EntityType entityType = (EntityType) args.get("mob");
                player.getWorld().spawnEntity(player.getLocation(), entityType);
            })
            .register();
        // #endregion registerSpawnMobCommand

        // #region createSafePotionEffectArguments
        List<Argument<?>> safeArgs = new ArrayList<>();
        safeArgs.add(new EntitySelectorArgument.OnePlayer("target"));
        safeArgs.add(new PotionEffectArgument("potioneffect").replaceSafeSuggestions(dev.jorel.commandapi.arguments.SafeSuggestions.suggest(
            info -> {
                Player target = (Player) info.previousArgs().get(0);

                // Convert PotionEffect[] into PotionEffectType[]
                return target.getActivePotionEffects().stream()
                    .map(PotionEffect::getType)
                    .toArray(PotionEffectType[]::new);
            })
        ));
        // #endregion createSafePotionEffectArguments

        // #region registerRemoveEffectCommand
        new CommandAPICommand("removeeffect")
            .withArguments(safeArgs)
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");
                PotionEffectType potionEffect = (PotionEffectType) args.get("potioneffect");
                target.removePotionEffect(potionEffect);
            })
            .register();
        // #endregion registerRemoveEffectCommand
    }
}
