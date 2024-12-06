package annotations;

import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.arguments.ADoubleArgument;
import dev.jorel.commandapi.annotations.arguments.AEntitySelectorArgument;
import dev.jorel.commandapi.annotations.arguments.AFloatArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.ALiteralArgument;
import dev.jorel.commandapi.annotations.arguments.ALocation2DArgument;
import dev.jorel.commandapi.annotations.arguments.ALocationArgument;
import dev.jorel.commandapi.annotations.arguments.ALongArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AScoreHolderArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import dev.jorel.commandapi.arguments.LocationType;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

import static annotations.WarpCommand.warps;

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
