package createcommands.arguments.suggestions

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.EntityTypeArgument
import dev.jorel.commandapi.arguments.PotionEffectArgument
import dev.jorel.commandapi.arguments.RecipeArgument
import dev.jorel.commandapi.arguments.SafeSuggestions
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Bukkit.getServer
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffectType

fun safeSuggestions() {
    val plugin = object : JavaPlugin() {}
    // #region registerCustomItem
    // Create our itemstack
    val emeraldSword = ItemStack(Material.DIAMOND_SWORD)
    val meta = emeraldSword.itemMeta
    meta?.setDisplayName("Emerald Sword")
    meta?.isUnbreakable = true
    emeraldSword.itemMeta = meta

    // Create and register our recipe
    val emeraldSwordRecipe = ShapedRecipe(NamespacedKey(plugin, "emerald_sword"), emeraldSword)
    emeraldSwordRecipe.shape(
        "AEA",
        "AEA",
        "ABA"
    )
    emeraldSwordRecipe.setIngredient('A', Material.AIR)
    emeraldSwordRecipe.setIngredient('E', Material.EMERALD)
    emeraldSwordRecipe.setIngredient('B', Material.BLAZE_ROD)
    getServer().addRecipe(emeraldSwordRecipe)

    // Omitted, more itemstacks and recipes
    // #endregion registerCustomItem

    // #region registerCommandWithSafeSuggestions
    // Safely override with the recipe we've defined
    val arguments = listOf<Argument<*>>(
        RecipeArgument("recipe").replaceSafeSuggestions(SafeSuggestions.suggest {
            arrayOf(emeraldSwordRecipe /* Other recipes here */)
        })
    )

    // Register our command
    CommandAPICommand("giverecipe")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val recipe = args["recipe"] as Recipe
            player.inventory.addItem(recipe.result)
        })
        .register()
    // #endregion registerCommandWithSafeSuggestions

    // #region createForbiddenMobsList
    val forbiddenMobs = listOf<EntityType>(EntityType.ENDER_DRAGON, EntityType.WITHER)
    val allowedMobs = EntityType.entries.toMutableList()
    allowedMobs.removeAll(forbiddenMobs) // Now contains everything except ender dragon and wither
    // #endregion createForbiddenMobsList

    // #region createSafeArguments
    val safeArguments = listOf<Argument<*>>(
        EntityTypeArgument("mob").replaceSafeSuggestions(SafeSuggestions.suggest { info ->
            if (info.sender().isOp) {
                // All entity types
                EntityType.entries.toTypedArray()
            } else {
                // Only allowedMobs
                allowedMobs.toTypedArray()
            }
        })
    )
    // #endregion createSafeArguments

    // #region registerSpawnMobCommand
    CommandAPICommand("spawnmob")
        .withArguments(safeArguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val entityType = args["mob"] as EntityType
            player.world.spawnEntity(player.location, entityType)
        })
        .register()
    // #endregion registerSpawnMobCommand

    // #region createSafePotionEffectArguments
    val safeArgs = mutableListOf<Argument<*>>()
    safeArgs.add(EntitySelectorArgument.OnePlayer("target"))
    safeArgs.add(
        PotionEffectArgument("potioneffect").replaceSafeSuggestions(SafeSuggestions.suggest { info ->
            val target = info.previousArgs()["target"] as Player

            // Convert PotionEffect[] into PotionEffectType[]
            target.activePotionEffects.map { it.type }.toTypedArray()
        })
    )
    // #endregion createSafePotionEffectArguments

    // #region registerRemoveEffectCommand
    CommandAPICommand("removeeffect")
        .withArguments(safeArgs)
        .executesPlayer(PlayerCommandExecutor { _, args ->
            val target = args["target"] as Player
            val potionEffect = args["potioneffect"] as PotionEffectType
            target.removePotionEffect(potionEffect)
        })
        .register()
    // #endregion registerRemoveEffectCommand
}