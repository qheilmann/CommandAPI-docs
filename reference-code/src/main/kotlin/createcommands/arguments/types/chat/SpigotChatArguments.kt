package createcommands.arguments.types.chat

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ChatColorArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.chatColorArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.ChatColor

fun spigotChatArguments() {
    // #region chatColorArgumentExample
    CommandAPICommand("namecolor")
        .withArguments(ChatColorArgument("chatColor"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val color = args["chatColor"] as ChatColor
            player.setDisplayName("$color${player.name}")
        })
        .register()
    // #endregion chatColorArgumentExample
}

fun spigotChatArgumentsDSL() {
    // #region chatColorArgumentExampleDSL
    commandAPICommand("namecolor") {
        chatColorArgument("chatcolor")
        playerExecutor { player, args ->
            val color = args["chatcolor"] as ChatColor
            player.setDisplayName("$color${player.name}")
        }
    }
    // #endregion chatColorArgumentExampleDSL
}