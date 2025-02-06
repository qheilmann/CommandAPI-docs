package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.AsyncOfflinePlayerArgument
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.EntityTypeArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.SafeSuggestions
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.asyncOfflinePlayerArgument
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentManyEntities
import dev.jorel.commandapi.kotlindsl.entityTypeArgument
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.concurrent.CompletableFuture

fun entitiesArguments() {
    // #region entitySelectorArgumentExample
    CommandAPICommand("remove")
        // Using a collective entity selector to select multiple entities
        .withArguments(EntitySelectorArgument.ManyEntities("entities"))
        .executes(NormalExecutor<CommandSender, Any> { sender, args ->
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
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val target = args["target"] as Player
            player.teleport(target)
        })
        .register()
    // #endregion noSelectorSuggestionsExample

    // #region playedBeforeArgumentExample
    CommandAPICommand("playedbefore")
        .withArguments(AsyncOfflinePlayerArgument("player"))
        .executes(NormalExecutor<CommandSender, Any> { sender, args ->
            val player = args["player"] as CompletableFuture<OfflinePlayer>

            // Directly sends a message to the sender, indicating that the command is running to prevent confusion
            sender.sendMessage("Checking if the player has played before...")

            player.thenAccept { offlinePlayer ->
                if (offlinePlayer.hasPlayedBefore()) {
                    sender.sendMessage("Player has played before")
                } else {
                    sender.sendMessage("Player has never played before")
                }
            }.exceptionally { throwable ->
                // We have to partly handle exceptions ourselves, since we are using a CompletableFuture
                val cause = throwable.cause
                val rootCause = if (cause is RuntimeException) cause.cause else cause

                sender.sendMessage(rootCause?.message ?: "An error occurred")
                null
            }
        })
        .register()
    // #endregion playedBeforeArgumentExample

    // #region entityTypeArgumentExample
    CommandAPICommand("spawnmob")
        .withArguments(EntityTypeArgument("entity"))
        .withArguments(IntegerArgument("amount", 1, 100)) // Prevent spawning too many entities
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
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

    // #region playedBeforeArgumentExampleDSL
    commandAPICommand("playedbefore") {
        asyncOfflinePlayerArgument("player")
        anyExecutor { sender, args ->
            val player = args["player"] as CompletableFuture<OfflinePlayer>

            // Directly sends a message to the sender, indicating that the command is running to prevent confusion
            sender.sendMessage("Checking if the player has played before...")

            player.thenAccept { offlinePlayer ->
                if (offlinePlayer.hasPlayedBefore()) {
                    sender.sendMessage("Player has played before")
                } else {
                    sender.sendMessage("Player has never played before")
                }
            }.exceptionally { throwable ->
                // We have to partly handle exceptions ourselves, since we are using a CompletableFuture
                val cause = throwable.cause
                val rootCause = if (cause is RuntimeException) cause.cause else cause

                sender.sendMessage(rootCause?.message ?: "An error occurred")
                null
            }
        }
    }
    // #endregion playedBeforeArgumentExampleDSL

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