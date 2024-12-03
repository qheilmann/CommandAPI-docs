package createcommands.functionsandtags;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FunctionArgument;
import dev.jorel.commandapi.wrappers.FunctionWrapper;

class FunctionArguments {
    {
        // #region functionArgumentsExample
        new CommandAPICommand("runfunction")
            .withArguments(new FunctionArgument("function"))
            .executes((sender, args) -> {
                FunctionWrapper[] functions = (FunctionWrapper[]) args.get("function");

                // Run all functions in our FunctionWrapper[]
                for (FunctionWrapper function : functions) {
                    function.run();
                }
            })
            .register();
        // #endregion functionArgumentsExample
    }
}
