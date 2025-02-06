package createcommands.arguments.types.literal

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.argument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.textArgument
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun literalArguments() {
    // #region showLiteralArgumentsIsNotListed
    CommandAPICommand("mycommand")
        .withArguments(LiteralArgument("hello"))
        .withArguments(TextArgument("text"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            // This gives the variable "text" the contents of the TextArgument, and not the literal "hello"
            val text = args[0] as String
        })
        .register()
    // #endregion showLiteralArgumentsIsNotListed

    // #region helperMethodsExample
    CommandAPICommand("mycommand")
        .withArguments(LiteralArgument.of("hello"))
        .withArguments(TextArgument("text"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val text = args[0] as String
        })
        .register()

    CommandAPICommand("mycommand")
        .withArguments(LiteralArgument.literal("hello"))
        .withArguments(TextArgument("text"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val text = args[0] as String
        })
        .register()
    // #endregion helperMethodsExample

    // #region literalArgumentsExample
    // Create a map of gamemode names to their respective objects
    val gamemodes = mapOf(
        "adventure" to GameMode.ADVENTURE,
        "creative" to GameMode.CREATIVE,
        "spectator" to GameMode.SPECTATOR,
        "survival" to GameMode.SURVIVAL
    )

    // Iterate over the map
    for ((key, _) in gamemodes) {
        // Register the command as usual
        CommandAPICommand("changegamemode")
            .withArguments(LiteralArgument(key))
            .executesPlayer(NormalExecutor<Player, Any> { player, _ ->
                // Retrieve the object from the map via the key and NOT the args[]
                player.gameMode = gamemodes[key]!!
            })
            .register()
    }
    // #endregion literalArgumentsExample
}

fun literalArgumentsDSL() {
    // #region showLiteralArgumentsIsNotListedDSL
    commandAPICommand("mycommand") {
        literalArgument("hello")
        textArgument("text")
        anyExecutor { _, args ->
            // This gives the variable "text" the contents of the TextArgument, and not the literal "hello"
            val text = args[0] as String
        }
    }
    // #endregion showLiteralArgumentsIsNotListedDSL

    // #region helperMethodsExampleDSL
    commandAPICommand("mycommand") {
        argument(LiteralArgument.of("hello"))
        textArgument("text")
        anyExecutor { _, args ->
            val text = args[0] as String
        }
    }

    commandAPICommand("mycommand") {
        argument(LiteralArgument.literal("hello"))
        textArgument("text")
        anyExecutor { _, args ->
            val text = args[0] as String
        }
    }
    // #endregion helperMethodsExampleDSL

    // #region literalArgumentsExampleDSL
    // Create a map of gamemode names to their respective objects
    val gamemodes = mapOf(
        "adventure" to GameMode.ADVENTURE,
        "creative" to GameMode.CREATIVE,
        "spectator" to GameMode.SPECTATOR,
        "survival" to GameMode.SURVIVAL
    )

    // Iterate over the map
    for ((key, _) in gamemodes) {
        // Register the command as usual
        commandAPICommand("changegamemode") {
            literalArgument(key)
            playerExecutor { player, args ->
                // Retrieve the object from the map via the key and NOT the args[]
                player.gameMode = gamemodes[key]!!
            }
        }

    }
    // #endregion literalArgumentsExampleDSL
}