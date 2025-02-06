package kotlindsl

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.entity.Player

fun delegatedProperties() {
    // #region delegatedPropertiesExample
    CommandAPICommand("mycommand")
        .withArguments(StringArgument("string"))
        .withArguments(PlayerArgument("target"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val string: String by args
            val target: Player by args
            // Implementation...
        })
        .register()
    // #endregion delegatedPropertiesExample
}

fun delegatedPropertiesDSL() {
    // #region delegatedPropertiesExampleDSL
    commandAPICommand("mycommand") {
        stringArgument("string")
        playerArgument("target")
        playerExecutor { player, args ->
            val string: String by args
            val target: Player by args
            // Implementation...
        }
    }
    // #endregion delegatedPropertiesExampleDSL
}