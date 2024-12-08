package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.SoundArgument;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;

class SoundArguments {
    static {
        // #region soundArgumentsExample
        new CommandAPICommand("sound")
            .withArguments(new SoundArgument("sound"))
            .executesPlayer((player, args) -> {
                player.getWorld().playSound(player.getLocation(), (Sound) args.get("sound"), 100.0f, 1.0f);
            })
            .register();
        // #endregion soundArgumentsExample

        // #region soundArgumentsNamespacedKeyExample
        new CommandAPICommand("sound")
            .withArguments(new SoundArgument.NamespacedKey("sound"))
            .executesPlayer((player, args) -> {
                player.getWorld().playSound(player.getLocation(), ((NamespacedKey) args.get("sound")).asString(), 100.0f, 1.0f);
            })
            .register();
        // #endregion soundArgumentsNamespacedKeyExample
    }
}
