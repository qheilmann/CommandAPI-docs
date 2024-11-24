package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.EntityCommandExecutor
import dev.jorel.commandapi.executors.ExecutorType
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Bukkit
import org.bukkit.command.ProxiedCommandSender
import org.bukkit.entity.LivingEntity

fun broadcastExample() {
    // #region broadcastExample
    // Create our command
    CommandAPICommand("broadcastmsg")
        .withArguments(GreedyStringArgument("message"))     // The arguments
        .withAliases("broadcast", "broadcastmessage")       // Command aliases
        .withPermission(CommandPermission.OP)               // Required permissions
        .executes(CommandExecutor { _, args ->
            val message = args["message"] as String
            Bukkit.getServer().broadcastMessage(message)
        })
        .register()
    // #endregion broadcastExample
}

fun suicideExample() {
    // #region suicideExample
    CommandAPICommand("suicide")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.setHealth(0.0)
        })
        .register()
    // #endregion suicideExample
}

fun differentImplExample() {
    // #region differentImplExample
    CommandAPICommand("suicide")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.setHealth(0.0)
        })
        .executesEntity(EntityCommandExecutor { entity, _ ->
            entity.world.createExplosion(entity.location, 4f)
            entity.remove()
        })
        .register()
    // #endregion differentImplExample
}

fun sameImplExample() {
    // #region sameImplExample
    CommandAPICommand("suicide")
        .executes(CommandExecutor { sender, _ ->
            val entity = (if (sender is ProxiedCommandSender) sender.callee else sender) as LivingEntity
            entity.setHealth(0.0)
        }, ExecutorType.PLAYER, ExecutorType.PROXY)
        .register()
    // #endregion sameImplExample
}