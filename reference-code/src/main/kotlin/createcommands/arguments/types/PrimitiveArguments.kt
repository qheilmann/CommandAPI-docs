package createcommands.arguments.types

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.BooleanArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.argument
import dev.jorel.commandapi.kotlindsl.booleanArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

val primitiveArguments = object : JavaPlugin() {
    override fun onEnable() {
        // #region primitiveArgumentsExample
        // Load keys from a config file
        val configKeys: Array<String> = config.getKeys(true).toTypedArray()

        // Register our command
        CommandAPICommand("editconfig")
            .withArguments(
                TextArgument("config-key").replaceSuggestions(
                    ArgumentSuggestions.strings { _ -> configKeys })
            )
            .withArguments(BooleanArgument("value"))
            .executes(NormalExecutor<CommandSender, Any> { _, args ->
                // Update the config with the boolean argument
                config.set(args["config-key"] as String, args["value"] as Boolean)
            })
            .register()
        // #endregion primitiveArgumentsExample
    }
}

val primitiveArgumentsDSL = object : JavaPlugin() {
    override fun onEnable() {
        // #region primitiveArgumentsExampleDSL
        // Load keys from a config file
        val configKeys: Array<String> = getConfig().getKeys(true).toTypedArray()

        // Register our command
        commandAPICommand("editconfig") {
            argument(
                TextArgument("config-key").replaceSuggestions(
                    ArgumentSuggestions.strings { configKeys })
            )
            booleanArgument("value")
            anyExecutor { _, args ->
                // Update the config with the boolean argument
                getConfig().set(args["config-key"] as String, args["value"] as Boolean)
            }
        }
        // #endregion primitiveArgumentsExampleDSL
    }
}