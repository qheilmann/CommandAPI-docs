package createcommands.functionsandtags;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FunctionArgument;
import dev.jorel.commandapi.wrappers.FunctionWrapper;

class FunctionWrapperRef {
    {
        // #region runExample
        new CommandAPICommand("runfunc")
            .withArguments(new FunctionArgument("function"))
            .executes((sender, args) -> {
                FunctionWrapper[] functions = (FunctionWrapper[]) args.get("function");
                for (FunctionWrapper function : functions) {
                    function.run(); // The command executor in this case is 'sender'
                }
            })
            .register();
        // #endregion runExample
    }
}
