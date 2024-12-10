package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.executors.ProxyCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.proxyExecutor
import org.bukkit.entity.LivingEntity

fun proxySender() {
    // #region simpleKillCommandExample
    CommandAPICommand("killme")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.health = 0.0
        })
        .register()
    // #endregion simpleKillCommandExample

    // #region proxyKillCommandExample
    CommandAPICommand("killme")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.health = 0.0
        })
        .executesProxy(ProxyCommandExecutor { proxy, _ ->
            // Check if the callee (target) is an Entity and kill it
            if (proxy.callee is LivingEntity) {
                (proxy.callee as LivingEntity).health = 0.0
            }
        })
        .register()
    // #endregion proxyKillCommandExample
}

fun proxySenderDSL() {
    // #region simpleKillCommandExampleDSL
    commandAPICommand("killme") {
        playerExecutor { player, _ ->
            player.health = 0.0
        }
    }
    // #endregion simpleKillCommandExampleDSL

    // #region proxyKillCommandExampleDSL
    commandAPICommand("killme") {
        playerExecutor { player, _ ->
            player.health = 0.0
        }
        proxyExecutor { proxy, _ ->
            // Check if the callee (target) is an Entity and kill it
            if (proxy.callee is LivingEntity) {
                (proxy.callee as LivingEntity).health = 0.0
            }
        }
    }
    // #endregion proxyKillCommandExampleDSL
}