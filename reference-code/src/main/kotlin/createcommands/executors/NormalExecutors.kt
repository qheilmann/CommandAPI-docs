package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.ExecutorType
import dev.jorel.commandapi.executors.NormalExecutor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.ProxiedCommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

fun normalExecutors() {
    // #region broadcastExample
    // Create our command
    CommandAPICommand("broadcastmsg")
        .withArguments(GreedyStringArgument("message"))     // The arguments
        .withAliases("broadcast", "broadcastmessage")       // Command aliases
        .withPermission(CommandPermission.OP)               // Required permissions
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val message = args["message"] as String
            Bukkit.getServer().broadcastMessage(message)
        })
        .register()
    // #endregion broadcastExample

    // #region suicideExample
    CommandAPICommand("suicide")
        .executesPlayer(NormalExecutor<Player, Any> { player, _ ->
            player.health = 0.0
        })
        .register()
    // #endregion suicideExample

    // #region differentImplExample
    CommandAPICommand("suicide")
        .executesPlayer(NormalExecutor<Player, Any> { player, _ ->
            player.health = 0.0
        })
        .executesEntity(NormalExecutor<Entity, Any> { entity, _ ->
            entity.world.createExplosion(entity.location, 4f)
            entity.remove()
        })
        .register()
    // #endregion differentImplExample

    // #region sameImplExample
    CommandAPICommand("suicide")
        .executes(NormalExecutor<CommandSender, Any> { sender, _ ->
            val entity = (if (sender is ProxiedCommandSender) sender.callee else sender) as LivingEntity
            entity.health = 0.0
        }, ExecutorType.PLAYER, ExecutorType.PROXY)
        .register()
    // #endregion sameImplExample
}