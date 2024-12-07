package createcommands.functionsandtags

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.FunctionArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.wrappers.FunctionWrapper

fun functionWrapper() {
    // #region runExample
    CommandAPICommand("runfunc")
        .withArguments(FunctionArgument("function"))
        .executes(CommandExecutor { _, args ->
            val functions = args["function"] as Array<FunctionWrapper>
            for (function in functions) {
                function.run() // The command executor in this case is 'sender'
            }
        })
        .register()
    // #endregion runExample
}