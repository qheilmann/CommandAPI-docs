package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.SoundArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.soundArgument
import org.bukkit.NamespacedKey
import org.bukkit.Sound

fun soundArguments() {
    // #region soundArgumentsExample
    CommandAPICommand("sound")
        .withArguments(SoundArgument("sound"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            player.world.playSound(player.location, args["sound"] as Sound, 100.0f, 1.0f)
        })
        .register()
    // #endregion soundArgumentsExample

    // #region soundArgumentsNamespacedKeyExample
    CommandAPICommand("sound")
        .withArguments(SoundArgument.NamespacedKey("sound"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            player.world.playSound(player.location, (args["sound"] as NamespacedKey).asString(), 100.0f, 1.0f)
        })
        .register()
    // #endregion soundArgumentsNamespacedKeyExample
}

fun soundArgumentsDSL() {
    // #region soundArgumentsExampleDSL
    commandAPICommand("sound") {
        soundArgument("sound")
        playerExecutor { player, args ->
            player.world.playSound(player.location, args["sound"] as Sound, 100.0f, 1.0f)
        }
    }
    // #endregion soundArgumentsExampleDSL

    // #region soundArgumentsNamespacedKeyExampleDSL
    commandAPICommand("sound") {
        soundArgument("sound", true)
        playerExecutor { player, args ->
            player.world.playSound(player.location, (args["sound"] as NamespacedKey).asString(), 100.0f, 1.0f)
        }
    }
    // #endregion soundArgumentsNamespacedKeyExampleDSL
}