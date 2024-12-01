package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.RecipeArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ComplexRecipe

fun recipeArguments() {
    // #region getResultExample
    CommandAPICommand("giverecipe")
        .withArguments(RecipeArgument("recipe"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val recipe = args["recipe"] as ComplexRecipe
            player.inventory.addItem(recipe.result)
        })
        .register()
    // #endregion getResultExample

    // #region getKeyExample
    CommandAPICommand("unlockrecipe")
        .withArguments(PlayerArgument("player"))
        .withArguments(RecipeArgument("recipe"))
        .executes(CommandExecutor { _, args ->
            val target = args["player"] as Player
            val recipe = args["recipe"] as ComplexRecipe

            target.discoverRecipe(recipe.key)
        })
        .register()
    // #endregion getKeyExample
}

fun recipeArgumentsDSL() {
    // #region getResultExampleDSL
    commandAPICommand("giverecipe") {
        recipeArgument("recipe")
        playerExecutor { player, args ->
            val recipe = args["recipe"] as ComplexRecipe
            player.inventory.addItem(recipe.result)
        }
    }
    // #endregion getResultExampleDSL

    // #region getKeyExampleDSL
    commandAPICommand("unlockrecipe") {
        playerArgument("player")
        recipeArgument("recipe")
        anyExecutor { _, args ->
            val target = args["player"] as Player
            val recipe = args["recipe"] as ComplexRecipe

            target.discoverRecipe(recipe.key)
        }
    }
    // #endregion getKeyExampleDSL
}