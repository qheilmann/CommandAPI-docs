package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.AsyncOfflinePlayerArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.EntityTypeArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.SafeSuggestions;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

class EntitiesArguments {
    static {
        // #region entitySelectorArgumentExample
        new CommandAPICommand("remove")
            // Using a collective entity selector to select multiple entities
            .withArguments(new EntitySelectorArgument.ManyEntities("entities"))
            .executes((sender, args) -> {
                // Parse the argument as a collection of entities (as stated above in the documentation)
                @SuppressWarnings("unchecked")
                Collection<Entity> entities = (Collection<Entity>) args.get("entities");

                sender.sendMessage("Removed " + entities.size() + " entities");
                for (Entity e : entities) {
                    e.remove();
                }
            })
            .register();
        // #endregion entitySelectorArgumentExample

        // #region buildNoSelectorSuggestions
        Argument<?> noSelectorSuggestions = new PlayerArgument("target")
            .replaceSafeSuggestions(SafeSuggestions.suggest(info ->
                Bukkit.getOnlinePlayers().toArray(new Player[0])
            ));
        // #endregion buildNoSelectorSuggestions

        // #region noSelectorSuggestionsExample
        new CommandAPICommand("warp")
            .withArguments(noSelectorSuggestions)
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");
                player.teleport(target);
            })
            .register();
        // #endregion noSelectorSuggestionsExample

        // #region playedBeforeArgumentExample
        new CommandAPICommand("playedbefore")
            .withArguments(new AsyncOfflinePlayerArgument("player"))
            .executes((sender, args) -> {
                CompletableFuture<OfflinePlayer> player = (CompletableFuture<OfflinePlayer>) args.get("player");

                // Directly sends a message to the sender, indicating that the command is running to prevent confusion
                sender.sendMessage("Checking if the player has played before...");

                player.thenAccept(offlinePlayer -> {
                    if (offlinePlayer.hasPlayedBefore()) {
                        sender.sendMessage("Player has played before");
                    } else {
                        sender.sendMessage("Player has never played before");
                    }
                }).exceptionally(throwable -> {
                    // We have to partly handle exceptions ourselves, since we are using a CompletableFuture
                    Throwable cause = throwable.getCause();
                    Throwable rootCause = cause instanceof RuntimeException ? cause.getCause() : cause;

                    sender.sendMessage(rootCause.getMessage());
                    return null;
                });
            })
            .register();
        // #endregion playedBeforeArgumentExample

        // #region entityTypeArgumentExample
        new CommandAPICommand("spawnmob")
            .withArguments(new EntityTypeArgument("entity"))
            .withArguments(new IntegerArgument("amount", 1, 100)) // Prevent spawning too many entities
            .executesPlayer((Player player, CommandArguments args) -> {
                for (int i = 0; i < (int) args.get("amount"); i++) {
                    player.getWorld().spawnEntity(player.getLocation(), (EntityType) args.get("entity"));
                }
            })
            .register();
        // #endregion entityTypeArgumentExample
    }
}
