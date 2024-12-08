package createcommands.executors;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.entity.LivingEntity;

class ProxySender {
    static {
        // #region simpleKillCommandExample
        new CommandAPICommand("killme")
            .executesPlayer((player, args) -> {
                player.setHealth(0);
            })
            .register();
        // #endregion simpleKillCommandExample

        // #region proxyKillCommandExample
        new CommandAPICommand("killme")
            .executesPlayer((player, args) -> {
                player.setHealth(0);
            })
            .executesProxy((proxy, args) -> {
                // Check if the callee (target) is an Entity and kill it
                if (proxy.getCallee() instanceof LivingEntity target) {
                    target.setHealth(0);
                }
            })
            .register();
        // #endregion proxyKillCommandExample
    }
}
