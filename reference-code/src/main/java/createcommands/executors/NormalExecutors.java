package createcommands.executors;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.executors.ExecutorType;
import org.bukkit.Bukkit;
import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.entity.LivingEntity;

class NormalExecutors {
    static {
        // #region broadcastExample
        // Create our command
        new CommandAPICommand("broadcastmsg")
            .withArguments(new GreedyStringArgument("message")) // The arguments
            .withAliases("broadcast", "broadcastmessage")       // Command aliases
            .withPermission(CommandPermission.OP)               // Required permissions
            .executes((sender, args) -> {
                String message = (String) args.get("message");
                Bukkit.getServer().broadcastMessage(message);
            })
            .register();
        // #endregion broadcastExample

        // #region suicideExample
        new CommandAPICommand("suicide")
            .executesPlayer((player, args) -> {
                player.setHealth(0);
            })
            .register();
        // #endregion suicideExample

        // #region differentImplExample
        new CommandAPICommand("suicide")
            .executesPlayer((player, args) -> {
                player.setHealth(0);
            })
            .executesEntity((entity, args) -> {
                entity.getWorld().createExplosion(entity.getLocation(), 4);
                entity.remove();
            })
            .register();
        // #endregion differentImplExample

        // #region sameImplExample
        new CommandAPICommand("suicide")
            .executes((sender, args) -> {
                LivingEntity entity;
                if (sender instanceof ProxiedCommandSender proxy) {
                    entity = (LivingEntity) proxy.getCallee();
                } else {
                    entity = (LivingEntity) sender;
                }
                entity.setHealth(0);
            }, ExecutorType.PLAYER, ExecutorType.PROXY)
            .register();
        // #endregion sameImplExample
    }
}
