package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.PotionEffectArgument;
import dev.jorel.commandapi.arguments.TimeArgument;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class PotionArguments {
    {
        // #region potionEffectArgumentsExample
        new CommandAPICommand("potion")
            .withArguments(new PlayerArgument("target"))
            .withArguments(new PotionEffectArgument("potion"))
            .withArguments(new TimeArgument("duration"))
            .withArguments(new IntegerArgument("strength"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("target");
                PotionEffectType potion = (PotionEffectType) args.get("potion");
                int duration = (int) args.get("duration");
                int strength = (int) args.get("strength");

                // Add the potion effect to the target player
                target.addPotionEffect(new PotionEffect(potion, duration, strength));
            })
            .register();
        // #endregion potionEffectArgumentsExample

        // #region potionEffectArgumentsNamespacedKeyExample
        new CommandAPICommand("potion")
            .withArguments(new PlayerArgument("target"))
            .withArguments(new PotionEffectArgument.NamespacedKey("potion"))
            .withArguments(new TimeArgument("duration"))
            .withArguments(new IntegerArgument("strength"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("target");
                NamespacedKey potionKey = (NamespacedKey) args.get("potion");
                int duration = (int) args.get("duration");
                int strength = (int) args.get("strength");

                PotionEffectType potion = PotionEffectType.getByKey(potionKey);

                // Add the potion effect to the target player
                target.addPotionEffect(new PotionEffect(potion, duration, strength));
            })
            .register();
        // #endregion potionEffectArgumentsNamespacedKeyExample
    }
}
