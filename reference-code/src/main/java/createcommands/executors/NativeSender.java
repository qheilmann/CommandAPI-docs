package createcommands.executors;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.CommandArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.WorldArgument;
import dev.jorel.commandapi.wrappers.CommandResult;
import dev.jorel.commandapi.wrappers.NativeProxyCommandSender;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

class NativeSender {
    static {
        // #region breakCommandExample
        new CommandAPICommand("break")
            .executesNative((sender, args) -> {
                Location location = sender.getLocation();
                if (location != null) {
                    location.getBlock().breakNaturally();
                }
            })
            .register();
        // #endregion breakCommandExample

        // #region constructorExample
        new CommandAPICommand("executeAs")
            .withArguments(
                new EntitySelectorArgument.OneEntity("target"),
                new LocationArgument("location"),
                new WorldArgument("world"),
                new CommandArgument("command")
            )
            .executes((caller, args) -> {
                CommandSender callee = (CommandSender) args.get("target");
                Location location = (Location) args.get("location");
                World world = (World) args.get("world");
                CommandResult command = (CommandResult) args.get("command");

                assert callee != null && location != null && world != null && command != null;

                command.execute(NativeProxyCommandSender.from(caller, callee, location, world));
            })
            .register();
        // #endregion constructorExample
    }
}
