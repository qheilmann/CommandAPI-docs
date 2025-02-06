package createcommands.arguments.types.chat

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.AdventureChatArgument
import dev.jorel.commandapi.arguments.AdventureChatColorArgument
import dev.jorel.commandapi.arguments.AdventureChatComponentArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.adventureChatArgument
import dev.jorel.commandapi.kotlindsl.adventureChatComponentArgument
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.chatColorArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.stringArgument
import dev.jorel.commandapi.kotlindsl.textArgument
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun adventureChatArguments() {
    // #region namedTextColorExample
    CommandAPICommand("namecolor")
        .withArguments(AdventureChatColorArgument("chatcolor"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val color = args["chatcolor"] as NamedTextColor
            player.displayName(Component.text().color(color).append(Component.text(player.name)).build())
        })
        .register()
    // #endregion namedTextColorExample

    // #region componentExample
    CommandAPICommand("showbook")
        .withArguments(PlayerArgument("target"))
        .withArguments(TextArgument("title"))
        .withArguments(StringArgument("author"))
        .withArguments(AdventureChatComponentArgument("contents"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val target = args["target"] as Player
            val title = args["title"] as String
            val author = args["author"] as String
            val content = args["contents"] as Component

            // Create a book and show it to the user (Requires Paper)
            val mybook = Book.book(Component.text(title), Component.text(author), content)
            target.openBook(mybook)
        })
        .register()
    // #endregion componentExample

    // #region chatArgumentExample
    CommandAPICommand("pbroadcast")
        .withArguments(AdventureChatArgument("message"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val message = args["message"] as Component

            // Broadcast the message to everyone with broadcast permissions.
            Bukkit.getServer().broadcast(message, Server.BROADCAST_CHANNEL_USERS)
            Bukkit.getServer().broadcast(message)
        })
        .register()
    // #endregion chatArgumentExample
}

fun adventureChatArgumentsDSL() {
    // #region namedTextColorExampleDSL
    commandAPICommand("namecolor") {
        chatColorArgument("chatcolor")
        playerExecutor { player, args ->
            val color = args["chatcolor"] as NamedTextColor
            player.displayName(Component.text().color(color).append(Component.text(player.name)).build())
        }
    }
    // #endregion namedTextColorExampleDSL

    // #region componentExampleDSL
    commandAPICommand("showbook") {
        playerArgument("target")
        textArgument("title")
        stringArgument("author")
        adventureChatComponentArgument("contents")
        anyExecutor { _, args ->
            val target = args["target"] as Player
            val title = args["title"] as String
            val author = args["author"] as String
            val content = args["contents"] as Component

            // Create a book and show it to the user (Requires Paper)
            val mybook = Book.book(Component.text(title), Component.text(author), content)
            target.openBook(mybook)
        }
    }
    // #endregion componentExampleDSL

    // #region chatArgumentExampleDSL
    commandAPICommand("pbroadcast") {
        adventureChatArgument("message")
        anyExecutor { _, args ->
            val message = args["message"] as Component

            // Broadcast the message to everyone with broadcast permissions.
            Bukkit.getServer().broadcast(message, Server.BROADCAST_CHANNEL_USERS)
            Bukkit.getServer().broadcast(message)
        }
    }
    // #endregion chatArgumentExampleDSL
}