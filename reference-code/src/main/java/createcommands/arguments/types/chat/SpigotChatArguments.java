package createcommands.arguments.types.chat;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ChatColorArgument;
import org.bukkit.ChatColor;

class SpigotChatArguments {
    {
        // #region chatColorArgumentExample
        new CommandAPICommand("namecolor")
            .withArguments(new ChatColorArgument("chatcolor"))
            .executesPlayer((player, args) -> {
                ChatColor color = (ChatColor) args.get("chatcolor");
                player.setDisplayName(color + player.getName());
            })
            .register();
        // #endregion chatColorArgumentExample
    }
}
