package createcommands.executors

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.NormalExecutor
import org.bukkit.command.CommandSender

fun handleFailures() {
    // #region handleFailuresExample
    // List of fruit
    val fruit = listOf("banana", "apple", "orange")

    // Register the command
    CommandAPICommand("getfruit")
        .withArguments(StringArgument("item").replaceSuggestions(ArgumentSuggestions.strings(fruit)))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val inputFruit = args["item"] as String

            if (fruit.any { it == inputFruit }) {
                // Do something with inputFruit
            } else {
                // The sender's input is not in the list of fruit
                throw CommandAPI.failWithString("That fruit doesn't exist!")
            }
        })
        .register()
    // #endregion handleFailuresExample
}