package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.proxyExecutor
import dev.jorel.commandapi.wrappers.NativeProxyCommandSender
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

fun proxySender() {
    // #region simpleKillCommandExample
    CommandAPICommand("killme")
        .executesPlayer(NormalExecutor<Player, Any> { player, _ ->
            player.health = 0.0
        })
        .register()
    // #endregion simpleKillCommandExample

    // #region proxyKillCommandExample
    CommandAPICommand("killme")
        .executesPlayer(NormalExecutor<Player, Any> { player, _ ->
            player.health = 0.0
        })
        .executesProxy(NormalExecutor<NativeProxyCommandSender, Any> { proxy, _ ->
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