package createcommands.arguments.types.chat

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.AdventureChatArgument
import dev.jorel.commandapi.arguments.ChatArgument
import dev.jorel.commandapi.executors.NormalExecutor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

fun chatPreview() {
    // #region chatPreviewAdventureExample
    CommandAPICommand("broadcast")
        .withArguments(AdventureChatArgument("message").withPreview { info ->
            // Convert parsed Component to plain text
            val plainText: String = PlainTextComponentSerializer.plainText().serialize(info.parsedInput() as Component)

            // Translate the & in plain text and generate a new Component
            LegacyComponentSerializer.legacyAmpersand().deserialize(plainText)
        } )
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            // The user still entered a legacy text. We need to properly convert this
            // to a Component by converting to plain text then to Component
            val plainText: String = PlainTextComponentSerializer.plainText().serialize(args["message"] as Component)
            Bukkit.broadcast(LegacyComponentSerializer.legacyAmpersand().deserialize(plainText))
        })
        .register()
    // #endregion chatPreviewAdventureExample

    // #region chatPreviewLegacyExample
    CommandAPICommand("broadcast")
        .withArguments(ChatArgument("message").withPreview { info ->
            // Convert parsed BaseComponent[] to plain text
            val plainText: String = BaseComponent.toPlainText(*info.parsedInput() as Array<BaseComponent>)

            // Translate the & in plain text and generate a new BaseComponent[]
            TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText))
        } )
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            // The user still entered a legacy text. We need to properly convert this
            // to a BaseComponent[] by converting to plain text then to BaseComponent[]
            val plainText: String = BaseComponent.toPlainText(*args["message"] as Array<BaseComponent>)
            val baseComponents: Array<BaseComponent> = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText))
            Bukkit.spigot().broadcast(*baseComponents)
        })
        .register()
    // #endregion chatPreviewLegacyExample

    // #region usePreviewAdventureExample
    CommandAPICommand("broadcast")
        .withArguments(AdventureChatArgument("message").usePreview(true).withPreview { info ->
            // Convert parsed Component to plain text
            val plainText = PlainTextComponentSerializer.plainText().serialize(info.parsedInput() as Component)

            // Translate the & in plain text and generate a new Component
            LegacyComponentSerializer.legacyAmpersand().deserialize(plainText)
        } )
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            Bukkit.broadcast(args["message"] as Component)
        })
        .register()
    // #endregion usePreviewAdventureExample

    // #region usePreviewLegacyExample
    CommandAPICommand("broadcast")
        .withArguments(ChatArgument("message").usePreview(true).withPreview { info ->
            // Convert parsed BaseComponent[] to plain text
            val plainText = BaseComponent.toPlainText(*info.parsedInput() as Array<BaseComponent>)

            // Translate the & in plain text and generate a new BaseComponent[]
            TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText))
        } )
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            Bukkit.spigot().broadcast(*args["message"] as Array<BaseComponent>)
        })
        .register()
    // #endregion usePreviewLegacyExample
}