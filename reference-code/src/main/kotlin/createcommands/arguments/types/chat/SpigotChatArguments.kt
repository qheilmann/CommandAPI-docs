package createcommands.arguments.types.chat

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ChatArgument
import dev.jorel.commandapi.arguments.ChatColorArgument
import dev.jorel.commandapi.arguments.ChatComponentArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.chatArgument
import dev.jorel.commandapi.kotlindsl.chatColorArgument
import dev.jorel.commandapi.kotlindsl.chatComponentArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

fun spigotChatArguments() {
    // #region chatColorArgumentExample
    CommandAPICommand("namecolor")
        .withArguments(ChatColorArgument("chatColor"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val color = args["chatColor"] as ChatColor
            player.setDisplayName("$color${player.name}")
        })
        .register()
    // #endregion chatColorArgumentExample

    // #region setPagesExample
    CommandAPICommand("makebook")
        .withArguments(PlayerArgument("player"))
        .withArguments(ChatComponentArgument("contents"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val player = args["player"] as Player
            val arr = args["contents"] as Array<BaseComponent>

            // Create book
            val item = ItemStack(Material.WRITTEN_BOOK)
            val meta = item.itemMeta as BookMeta
            meta.title = "Custom Book"
            meta.author = player.name
            meta.spigot().setPages(arr)
            item.itemMeta = meta

            // Give player the book
            player.inventory.addItem(item)
        })
        .register()
    // #endregion setPagesExample

    // #region chatArgumentExample
    CommandAPICommand("pbroadcast")
        .withArguments(ChatArgument("message"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val message = args["message"] as Array<BaseComponent>

            // Broadcast the message to everyone on the server
            Bukkit.getServer().spigot().broadcast(*message)
        })
        .register()
    // #endregion chatArgumentExample
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

    // #region setPagesExampleDSL
    commandAPICommand("makebook") {
        playerArgument("player")
        chatComponentArgument("contents")
        anyExecutor { _, args ->
            val player = args["player"] as Player
            val array = args["contents"] as Array<BaseComponent>

            // Create book
            val item = ItemStack(Material.WRITTEN_BOOK)
            val meta = item.itemMeta as BookMeta
            meta.title = "Custom Book"
            meta.author = player.name
            meta.spigot().setPages(array)
            item.itemMeta = meta

            // Give player the book
            player.inventory.addItem(item)
        }
    }
    // #endregion setPagesExampleDSL

    // #region chatArgumentExampleDSL
    commandAPICommand("pbroadcast") {
        chatArgument("message")
        anyExecutor { _, args ->
            val message = args["message"] as Array<BaseComponent>

            // Broadcast the message to everyone on the server
            Bukkit.getServer().spigot().broadcast(*message)
        }
    }
    // #endregion chatArgumentExampleDSL
}