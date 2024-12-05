package annotations;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Help;
import dev.jorel.commandapi.annotations.NeedsOp;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.ADoubleArgument;
import dev.jorel.commandapi.annotations.arguments.AEntitySelectorArgument;
import dev.jorel.commandapi.annotations.arguments.AFloatArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.ALiteralArgument;
import dev.jorel.commandapi.annotations.arguments.ALocation2DArgument;
import dev.jorel.commandapi.annotations.arguments.ALocationArgument;
import dev.jorel.commandapi.annotations.arguments.ALongArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import dev.jorel.commandapi.annotations.arguments.AScoreHolderArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import dev.jorel.commandapi.arguments.LocationType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

import static annotations.Intro.WarpCommand.warps;

class Annotations {
    class ClassExample {
        class AliasExample {
            // #region aliasClassExample
            @Command("teleport")
            @Alias({"tp", "tele"})
            public class TeleportCommand {
                // #endregion aliasClassExample
            }
        }

        class PermissionExample {
            // #region permissionClassExample
            @Command("teleport")
            @Permission("myplugin.tp")
            public class TeleportCommand {
                // #endregion permissionClassExample
            }
        }

        class NeedsOpExample {
            // #region needsOpClassExample
            @Command("teleport")
            @NeedsOp
            public class TeleportCommand {
                // #endregion needsOpClassExample
            }
        }

        class HelpExample {
            // #region helpClassExample
            @Command("teleport")
            @Help("Teleports yourself to another location")
            public class TeleportCommand {
                // #endregion helpClassExample
            }
        }

        class ShortHelpExample {
            // #region shortHelpClassExample
            @Command("teleport")
            @Help(value = "Teleports yourself to another location", shortDescription = "TP to a location")
            public class TeleportCommand {
                // #endregion shortHelpClassExample
            }
        }
    }

    class MethodExample {
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

        class SubcommandMethodExample {
            // #region subcommandMethodExample
            @Subcommand("create")
            @Permission("warps.create")
            public static void createWarp(Player player, @AStringArgument String warpName) {
                warps.put(warpName, player.getLocation());
            }
            // #endregion subcommandMethodExample

            // #region subcommandAliasesMethodExample
            @Subcommand({"teleport", "tp"})
            public static void teleport(Player player, @APlayerArgument OfflinePlayer target) {
                if(target.isOnline() && target instanceof Player onlineTarget) {
                    player.teleport(onlineTarget);
                }
            }
            // #endregion subcommandAliasesMethodExample
        }

        class PermissionMethodExample {
            // #region permissionMethodExample
            @Subcommand("create")
            @Permission("warps.create")
            public static void createWarp(Player player, @AStringArgument String warpName) {
                warps.put(warpName, player.getLocation());
            }
            // #endregion permissionMethodExample
        }
    }

    class ParameterExample {
        // #region simpleParameterExample
        @Default
        public static void warp(Player player, @AStringArgument String warpName) {
            player.teleport(warps.get(warpName));
        }
        // #endregion simpleParameterExample

        // #region numericalParameterExample
        @Default
        public static void command(CommandSender sender,
                                   @ADoubleArgument(min = 0.0, max = 10.0) double someDouble,
                                   @AFloatArgument(min = 5.0f, max = 10.0f) float someFloat,
                                   @AIntegerArgument(max = 100) int someInt,
                                   @ALongArgument(min = -10) long someLong
        ) {
            // Command implementation here
        }
        // #endregion numericalParameterExample

        // #region literalParameterExample
        @Default
        public static void command(CommandSender sender,
                                   @ALiteralArgument("myliteral") String literal,
                                   @AMultiLiteralArgument({"literal", "anotherliteral"}) String multipleLiterals
        ) {
            // Command implementation here
        }
        // #endregion literalParameterExample

        // #region otherParameterExample
        @Default
        public static void command(CommandSender sender,
                                   @ALocationArgument(LocationType.BLOCK_POSITION) Location location,
                                   @ALocation2DArgument(LocationType.PRECISE_POSITION) Location location2d,
                                   @AEntitySelectorArgument.ManyEntities Collection<Entity> entities,
                                   @AScoreHolderArgument.Multiple Collection<String> scoreHolders
        ) {
            // Command implementation here
        }
        // #endregion otherParameterExample
    }
}
