package createcommands.arguments;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.PotionEffectArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

class Arguments {
    {
        // #region registerArgumentsExample
        new CommandAPICommand("mycommand")
            .withArguments(new StringArgument("arg0"))
            .withArguments(new StringArgument("arg1"))
            .withArguments(new StringArgument("arg2"))
            // And so on
        ;
        // #endregion registerArgumentsExample

        // #region registerArgumentsVarargExample
        new CommandAPICommand("mycommand")
            .withArguments(
                new StringArgument("arg0"),
                new StringArgument("arg1"),
                new StringArgument("arg2")
            )
            // And so on
        // #endregion registerArgumentsVarargExample
        ;

        // #region registerArgumentsListExample
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new StringArgument("arg0"));
        arguments.add(new StringArgument("arg1"));
        arguments.add(new StringArgument("arg2"));

        new CommandAPICommand("mycommand")
            .withArguments(arguments)
            // And so on
        // #endregion registerArgumentsListExample
        ;

        // #region argumentCastExample
        List<Argument<?>> commandArguments = new ArrayList<>();
        commandArguments.add(new StringArgument("arg0"));
        commandArguments.add(new PotionEffectArgument("arg1"));
        commandArguments.add(new LocationArgument("arg2"));

        new CommandAPICommand("cmd")
            .withArguments(commandArguments)
            .executes((sender, args) -> {
                String stringArg = (String) args.get("arg0");
                PotionEffectType potionArg = (PotionEffectType) args.get("arg1");
                Location locationArg = (Location) args.get("arg2");
            })
            .register();
        // #endregion argumentCastExample
    }
}
