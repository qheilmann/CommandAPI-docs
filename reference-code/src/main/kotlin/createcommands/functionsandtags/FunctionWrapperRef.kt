package createcommands.functionsandtags

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.FunctionArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.wrappers.FunctionWrapper
import org.bukkit.command.CommandSender

fun functionWrapper() {
    // #region runExample
    CommandAPICommand("runfunc")
        .withArguments(FunctionArgument("function"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val functions = args["function"] as Array<FunctionWrapper>
            for (function in functions) {
                function.run() // The command executor in this case is 'sender'
            }
        })
        .register()
    // #endregion runExample
}