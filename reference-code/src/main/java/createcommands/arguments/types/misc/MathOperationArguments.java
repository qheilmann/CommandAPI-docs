package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.MathOperationArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.wrappers.MathOperation;
import org.bukkit.entity.Player;

class MathOperationArguments {
    {
        // #region mathOperationArgumentsExample
        new CommandAPICommand("changelevel")
            .withArguments(new PlayerArgument("player"))
            .withArguments(new MathOperationArgument("operation"))
            .withArguments(new IntegerArgument("value"))
            .executes((sender, args) -> {
                Player target = (Player) args.get("player");
                MathOperation op = (MathOperation) args.get("operation");
                int value = (int) args.get("value");

                target.setLevel(op.apply(target.getLevel(), value));
            })
            .register();
        // #endregion mathOperationArgumentsExample
    }
}
