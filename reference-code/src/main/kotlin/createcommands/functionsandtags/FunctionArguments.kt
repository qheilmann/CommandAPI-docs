package createcommands.functionsandtags

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.FunctionArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.functionArgument
import dev.jorel.commandapi.wrappers.FunctionWrapper

fun functionArguments() {
    // #region functionArgumentsExample
    CommandAPICommand("runfunction")
        .withArguments(FunctionArgument("function"))
        .executes(CommandExecutor { _, args ->
            val functions = args["function"] as Array<FunctionWrapper>

            // Run all functions in our FunctionWrapper[]
            for (function in functions) {
                function.run()
            }
        })
        .register()
    // #endregion functionArgumentsExample
}

fun functionArgumentsDSL() {
    // #region functionArgumentsExampleDSL
    commandAPICommand("runfunction") {
        functionArgument("function")
        anyExecutor { _, args ->
            val functions = args["function"] as Array<FunctionWrapper>

            // Run all functions in our FunctionWrapper[]
            for (function in functions) {
                function.run()
            }
        }
    }
    // #endregion functionArgumentsExampleDSL
}