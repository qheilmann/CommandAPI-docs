package createcommands.arguments.types.chat;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.AdventureChatArgument;
import dev.jorel.commandapi.arguments.ChatArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

class ChatPreview {
    {
        // #region chatPreviewAdventureExample
        new CommandAPICommand("broadcast")
            .withArguments(new AdventureChatArgument("message").withPreview(info -> {
                // Convert parsed Component to plain text
                String plainText = PlainTextComponentSerializer.plainText().serialize(info.parsedInput());

                // Translate the & in plain text and generate a new Component
                return LegacyComponentSerializer.legacyAmpersand().deserialize(plainText);
            }))
            .executesPlayer((player, args) -> {
                // The user still entered a legacy text. We need to properly convert this
                // to a Component by converting to plain text then to Component
                String plainText = PlainTextComponentSerializer.plainText().serialize((Component) args.get("broadcast"));
                Bukkit.broadcast(LegacyComponentSerializer.legacyAmpersand().deserialize(plainText));
            })
            .register();
        // #endregion chatPreviewAdventureExample

        // #region chatPreviewLegacyExample
        new CommandAPICommand("broadcast")
            .withArguments(new ChatArgument("message").withPreview(info -> {
                // Convert parsed BaseComponent[] to plain text
                String plainText = BaseComponent.toPlainText(info.parsedInput());

                // Translate the & in plain text and generate a new BaseComponent[]
                return TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText));
            }))
            .executesPlayer((player, args) -> {
                // The user still entered a legacy text. We need to properly convert this
                // to a BaseComponent[] by converting to plain text then to BaseComponent[]
                String plainText = BaseComponent.toPlainText((BaseComponent[]) args.get("message"));
                Bukkit.spigot().broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText)));
            })
            .register();
        // #endregion chatPreviewLegacyExample

        // #region usePreviewAdventureExample
        new CommandAPICommand("broadcast")
            .withArguments(new AdventureChatArgument("message").usePreview(true).withPreview(info -> {
                // Convert parsed Component to plain text
                String plainText = PlainTextComponentSerializer.plainText().serialize(info.parsedInput());

                // Translate the & in plain text and generate a new Component
                return LegacyComponentSerializer.legacyAmpersand().deserialize(plainText);
            }))
            .executesPlayer((player, args) -> {
                Bukkit.broadcast((Component) args.get("message"));
            })
            .register();
        // #endregion usePreviewAdventureExample

        // #region usePreviewLegacyExample
        new CommandAPICommand("broadcast")
            .withArguments(new ChatArgument("message").usePreview(true).withPreview(info -> {
                // Convert parsed BaseComponent[] to plain text
                String plainText = BaseComponent.toPlainText(info.parsedInput());

                // Translate the & in plain text and generate a new BaseComponent[]
                return TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plainText));
            }))
            .executesPlayer((player, args) -> {
                Bukkit.spigot().broadcast((BaseComponent[]) args.get("message"));
            })
            .register();
        // #endregion usePreviewLegacyExample
    }
}
