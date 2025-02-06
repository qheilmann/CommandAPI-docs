package createcommands.arguments

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun optionalArguments() {
    // #region simpleOptionalArgumentsExample
    CommandAPICommand("sayhi")
        .withOptionalArguments(PlayerArgument("target"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val target: Player? = args["target"] as Player?
            if (target != null) {
                target.sendMessage("Hi!")
            } else {
                player.sendMessage("Hi!")
            }
        })
        .register()
    // #endregion simpleOptionalArgumentsExample

    // #region getOptionalExample
    CommandAPICommand("sayhi")
        .withOptionalArguments(PlayerArgument("target"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val target: Player = args.getOptional("target").orElse(player) as Player
            target.sendMessage("Hi!")
        })
        .register()
    // #endregion getOptionalExample

    // #region argumentsAfterOptionalArgumentsExample
    CommandAPICommand("rate")
        .withOptionalArguments(StringArgument("topic").combineWith(IntegerArgument("rating", 0, 10)))
        .withOptionalArguments(PlayerArgument("target"))
        .executes(NormalExecutor<CommandSender, Any> { sender, args ->
            val topic: String? = args["topic"] as String?
            if (topic == null) {
                sender.sendMessage(
                    "Usage: /rate <topic> <rating> <player>(optional)",
                    "Select a topic to rate, then give a rating between 0 and 10",
                    "You can optionally add a player at the end to give the rating to"
                )
                return@NormalExecutor
            }

            // We know this is not null because rating is required if a topic is given
            val rating = args["rating"] as Int

            // The target player is optional, so give it a default here
            val target: CommandSender = args.getOptional("target").orElse(sender) as CommandSender

            target.sendMessage("Your $topic was rated: $rating/10")
        })
        .register()
    // #endregion argumentsAfterOptionalArgumentsExample
}

fun optionalArgumentsDSL() {
    // #region simpleOptionalArgumentsExampleDSL
    commandAPICommand("sayhi") {
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val target: Player? = args["target"] as Player?
            if (target != null) {
                target.sendMessage("Hi!")
            } else {
                player.sendMessage("Hi!")
            }
        }
    }
    // #endregion simpleOptionalArgumentsExampleDSL

    // #region getOptionalExampleDSL
    commandAPICommand("sayhi") {
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val target: Player = args.getOptional("target").orElse(player) as Player
            target.sendMessage("Hi!")
        }
    }
    // #endregion getOptionalExampleDSL

    // #region argumentsAfterOptionalArgumentsExampleDSL
    commandAPICommand("rate") {
        optionalArgument(StringArgument("topic").combineWith(IntegerArgument("rating", 0, 10)))
        playerArgument("target", optional = true)
        anyExecutor { sender, args ->
            val topic: String? = args["topic"] as String?
            if (topic == null) {
                sender.sendMessage(
                    "Usage: /rate <topic> <rating> <player>(optional)",
                    "Select a topic to rate, then give a rating between 0 and 10",
                    "You can optionally add a player at the end to give the rating to"
                )
                return@anyExecutor
            }

            // We know this is not null because rating is required if a topic is given
            val rating = args["rating"] as Int

            // The target player is optional, so give it a default here
            val target: CommandSender = args.getOptional("target").orElse(sender) as CommandSender

            target.sendMessage("Your $topic was rated: $rating/10")
        }
    }
    // #endregion argumentsAfterOptionalArgumentsExampleDSL
}