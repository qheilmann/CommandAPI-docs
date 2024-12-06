package annotations;

import static annotations.WarpCommand.warps;

class SubcommandMethodExample {
    // #region subcommandMethodExample
    @dev.jorel.commandapi.annotations.Subcommand("create")
    @dev.jorel.commandapi.annotations.Permission("warps.create")
    public static void createWarp(org.bukkit.entity.Player player, @dev.jorel.commandapi.annotations.arguments.AStringArgument String warpName) {
        warps.put(warpName, player.getLocation());
    }
    // #endregion subcommandMethodExample

    // #region subcommandAliasesMethodExample
    @dev.jorel.commandapi.annotations.Subcommand({"teleport", "tp"})
    public static void teleport(org.bukkit.entity.Player player, @dev.jorel.commandapi.annotations.arguments.APlayerArgument org.bukkit.OfflinePlayer target) {
        if (target.isOnline() && target instanceof org.bukkit.entity.Player onlineTarget) {
            player.teleport(onlineTarget);
        }
    }
    // #endregion subcommandAliasesMethodExample
}
