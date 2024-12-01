package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.WorldArgument;
import org.bukkit.Bukkit;
import org.bukkit.World;

class WorldArguments {
    {
        // #region worldArgumentsExample
        new CommandAPICommand("unloadworld")
            .withArguments(new WorldArgument("world"))
            .executes((sender, args) -> {
                World world = (World) args.get("world");

                // Unload the world (and save the world's chunks)
                Bukkit.getServer().unloadWorld(world, true);
            })
            .register();
        // #endregion worldArgumentsExample
    }
}
