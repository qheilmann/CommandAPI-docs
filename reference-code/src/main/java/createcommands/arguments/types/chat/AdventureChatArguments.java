package createcommands.arguments.types.chat;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.AdventureChatArgument;
import dev.jorel.commandapi.arguments.AdventureChatColorArgument;
import dev.jorel.commandapi.arguments.AdventureChatComponentArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

class AdventureChatArguments {
    {
        // #region namedTextColorExample
        new CommandAPICommand("namecolor")
            .withArguments(new AdventureChatColorArgument("chatcolor"))
            .executesPlayer((player, args) -> {
                NamedTextColor color = (NamedTextColor) args.get("chatcolor");
                player.displayName(Component.text().color(color).append(Component.text(player.getName())).build());
            })
            .register();
        // #endregion namedTextColorExample

        // #region componentExample
        new CommandAPICommand("showbook")
            .withArguments(new PlayerArgument("target"))
            .withArguments(new TextArgument("title"))
            .withArguments(new StringArgument("author"))
            .withArguments(new AdventureChatComponentArgument("contents"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("target");
                String title = (String) args.get("title");
                String author = (String) args.get("author");
                Component content = (Component) args.get("contents");

                // Create a book and show it to the user (Requires Paper)
                Book mybook = Book.book(Component.text(title), Component.text(author), content);
                target.openBook(mybook);
            })
            .register();
        // #endregion componentExample

        // #region chatArgumentExample
        new CommandAPICommand("pbroadcast")
            .withArguments(new AdventureChatArgument("message"))
            .executes((sender, args) -> {
                Component message = (Component) args.get("message");

                // Broadcast the message to everyone with broadcast permissions.
                Bukkit.getServer().broadcast(message, Server.BROADCAST_CHANNEL_USERS);
                Bukkit.getServer().broadcast(message);
            })
            .register();
        // #endregion chatArgumentExample
    }
}
