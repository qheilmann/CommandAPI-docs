package annotations;

import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static annotations.WarpCommand.warps;

class DefaultMethodExample {
    // #region defaultMethodExample
    @Default
    public static void warp(CommandSender sender) {
        sender.sendMessage("--- Warp help ---");
        sender.sendMessage("/warp - Show this help");
        sender.sendMessage("/warp <warp> - Teleport to <warp>");
        sender.sendMessage("/warp create <warpname> - Creates a warp at your current location");
    }
    // #endregion defaultMethodExample

    // #region defaultWithArgsMethodExample
    @Default
    public static void warp(Player player, @AStringArgument String warpName) {
        player.teleport(warps.get(warpName));
    }
    // #endregion defaultWithArgsMethodExample
}
