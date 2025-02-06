package createcommands.arguments.types.literal

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.MultiLiteralArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.multiLiteralArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.GameMode
import org.bukkit.entity.Player

fun multiliteralArguments() {
    // #region multiliteralArgumentsExample
    CommandAPICommand("gamemode")
        .withArguments(MultiLiteralArgument("gamemodes", "adventure", "creative", "spectator", "survival"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            // The literal string that the player enters IS available in the args[]
            when (args["gamemodes"] as String) {
                "adventure" -> player.gameMode = GameMode.ADVENTURE
                "creative" -> player.gameMode = GameMode.CREATIVE
                "spectator" -> player.gameMode = GameMode.SPECTATOR
                "survival" -> player.gameMode = GameMode.SURVIVAL
            }
        })
        .register()
    // #endregion multiliteralArgumentsExample
}

fun multiliteralArgumentsDSL() {
    // #region multiliteralArgumentsExampleDSL
    commandAPICommand("gamemode") {
        multiLiteralArgument(nodeName = "gamemodes", "adventure", "creative", "spectator", "survival") // Adding this for now, needed because ambiguous methods exist
        playerExecutor { player, args ->
            // The literal string that the player enters IS available in the args[]
            when (args["gamemodes"] as String) {
                "adventure" -> player.gameMode = GameMode.ADVENTURE
                "creative" -> player.gameMode = GameMode.CREATIVE
                "spectator" -> player.gameMode = GameMode.SPECTATOR
                "survival" -> player.gameMode = GameMode.SURVIVAL
            }
        }
    }
    // #endregion multiliteralArgumentsExampleDSL
}