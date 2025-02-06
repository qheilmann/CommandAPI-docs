package createcommands.arguments.suggestions

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.stringArgument
import dev.jorel.commandapi.kotlindsl.textArgument
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.CompletableFuture

fun asyncSuggestions() {
    val plugin = object : JavaPlugin() {}
    // @formatter:off
    // #region asyncSuggestionsExample
    CommandAPICommand("setconfig")
        .withArguments(StringArgument("key").replaceSuggestions(
            ArgumentSuggestions.stringsAsync { _ ->
                CompletableFuture.supplyAsync {
                    plugin.config.getKeys(false).toTypedArray()
                }
            }
        ))
        .withArguments(TextArgument("value"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val key = args["key"] as String
            val value = args["value"] as String
            plugin.config.set(key, value)
        })
        .register()
    // #endregion asyncSuggestionsExample
    // @formatter:on
}

fun asyncSuggestionsDSL() {
    val plugin: JavaPlugin = object : JavaPlugin() {}
    // #region asyncSuggestionsExampleDSL
    commandAPICommand("setconfig") {
        stringArgument("key") {
            replaceSuggestions(ArgumentSuggestions.stringsAsync { _ ->
                CompletableFuture.supplyAsync {
                    plugin.config.getKeys(false).toTypedArray()
                }
            })
        }
        textArgument("value")
        anyExecutor { _, args ->
            val key = args["key"] as String
            val value = args["value"] as String
            plugin.config.set(key, value)
        }
    }
    // #endregion asyncSuggestionsExampleDSL
}