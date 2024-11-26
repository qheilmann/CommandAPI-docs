package createcommands.arguments.types.position;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.LocationType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

class LocationArguments {
    {
        // #region centerPositionExample
        new LocationArgument("location", LocationType.PRECISE_POSITION, true);
        // #endregion centerPositionExample

        // #region doNotCenterPositionExample
        new LocationArgument("location", LocationType.PRECISE_POSITION, false);
        // #endregion doNotCenterPositionExample

        // #region breakCommandExample
        new CommandAPICommand("break")
            // We want to focus on blocks in particular, so use BLOCK_POSITION
            .withArguments(new LocationArgument("block", LocationType.BLOCK_POSITION))
            .executesPlayer((player, args) -> {
                Location location = (Location) args.get("block");
                location.getBlock().setType(Material.AIR);
            })
            .register();
        // #endregion breakCommandExample
    }
}
