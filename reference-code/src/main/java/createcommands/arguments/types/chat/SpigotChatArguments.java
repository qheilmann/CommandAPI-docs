package createcommands.arguments.types.chat;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ChatArgument;
import dev.jorel.commandapi.arguments.ChatColorArgument;
import dev.jorel.commandapi.arguments.ChatComponentArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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

        // #region setPagesExample
        new CommandAPICommand("makebook")
            .withArguments(new PlayerArgument("player"))
            .withArguments(new ChatComponentArgument("contents"))
            .executes((sender, args) -> {
                Player player = (Player) args.get("player");
                BaseComponent[] arr = (BaseComponent[]) args.get("contents");

                // Create book
                ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) is.getItemMeta();
                meta.setTitle("Custom Book");
                meta.setAuthor(player.getName());
                meta.spigot().setPages(arr);
                is.setItemMeta(meta);

                // Give player the book
                player.getInventory().addItem(is);
            })
            .register();
        // #endregion setPagesExample

        // #region chatArgumentExample
        new CommandAPICommand("pbroadcast")
            .withArguments(new ChatArgument("message"))
            .executes((sender, args) -> {
                BaseComponent[] message = (BaseComponent[]) args.get("message");

                // Broadcast the message to everyone on the server
                Bukkit.getServer().spigot().broadcast(message);
            })
            .register();
        // #endregion chatArgumentExample
    }
}
