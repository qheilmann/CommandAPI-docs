package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.EntityTypeArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.SafeSuggestions
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentManyEntities
import dev.jorel.commandapi.kotlindsl.entityTypeArgument
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

fun entitiesArguments() {
    // #region entitySelectorArgumentExample
    CommandAPICommand("remove")
        // Using a collective entity selector to select multiple entities
        .withArguments(EntitySelectorArgument.ManyEntities("entities"))
        .executes(CommandExecutor { sender, args ->
            // Parse the argument as a collection of entities (as stated above in the documentation)
            val entities = args["entities"] as Collection<Entity>

            sender.sendMessage("Removed ${entities.size} entities")
            for (e in entities) {
                e.remove()
            }
        })
        .register()
    // #endregion entitySelectorArgumentExample

    // #region buildNoSelectorSuggestions
    val noSelectorSuggestions = PlayerArgument("target")
        .replaceSafeSuggestions(SafeSuggestions.suggest {
            Bukkit.getOnlinePlayers().toTypedArray()
        })
    // #endregion buildNoSelectorSuggestions

    // #region noSelectorSuggestionsExample
    CommandAPICommand("warp")
        .withArguments(noSelectorSuggestions)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["target"] as Player
            player.teleport(target)
        })
        .register()
    // #endregion noSelectorSuggestionsExample

    // #region entityTypeArgumentExample
    CommandAPICommand("spawnmob")
        .withArguments(EntityTypeArgument("entity"))
        .withArguments(IntegerArgument("amount", 1, 100)) // Prevent spawning too many entities
        .executesPlayer(PlayerCommandExecutor { player, args ->
            for (i in 0 until args["amount"] as Int) {
                player.world.spawnEntity(player.location, args["entity"] as EntityType)
            }
        })
        .register()
    // #endregion entityTypeArgumentExample
}

fun entitiesArgumentsDSL() {
    // #region entitySelectorArgumentExampleDSL
    commandAPICommand("remove") {
        // Using a collective entity selector to select multiple entities
        entitySelectorArgumentManyEntities("entities")
        anyExecutor { sender, args ->
            // Parse the argument as a collection of entities (as stated above in the documentation)
            val entities = args["entities"] as Collection<Entity>

            sender.sendMessage("Removed ${entities.size} entities")
            for (e in entities) {
                e.remove()
            }
        }
    }
    // #endregion entitySelectorArgumentExampleDSL

    // #region entityTypeArgumentExampleDSL
    commandAPICommand("spawnmob") {
        entityTypeArgument("entity")
        integerArgument("amount", 1, 100) // Prevent spawning too many entities
        playerExecutor { player, args ->
            for (i in 0 until args["amount"] as Int) {
                player.world.spawnEntity(player.location, args["entity"] as EntityType)
            }
        }
    }
    // #endregion entityTypeArgumentExampleDSL
}