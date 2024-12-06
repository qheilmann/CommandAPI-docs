package internal

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.ParseResults
import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import dev.jorel.commandapi.Brigadier
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.util.concurrent.CompletableFuture

fun brigadierSuggestions() {
    // #region emojiCommandExample
    val emojis = mapOf(
        "☻" to "smile",
        "❤" to "heart",
        "🔥" to "fire",
        "★" to "star",
        "☠" to "death",
        "⚠" to "warning",
        "☀" to "sun",
        "☺" to "smile",
        "☹" to "frown",
        "✉" to "mail",
        "☂" to "umbrella",
        "✘" to "cross",
        "♪" to "music note (eighth)",
        "♬" to "music note (beamed sixteenth)",
        "♩" to "music note (quarter)",
        "♫" to "music note (beamed eighth)",
        "☄" to "comet",
        "✦" to "star",
        "🗡" to "sword",
        "🪓" to "axe",
        "🔱" to "trident",
        "🎣" to "fishing rod",
        "🏹" to "bow",
        "⛏" to "pickaxe",
        "🍖" to "food"
    )

    val messageArgument = GreedyStringArgument("message")
        .replaceSuggestions { info, builder ->
            // Only display suggestions at the very end character
            val newBuilder = builder.createOffset(builder.start + info.currentArg().length)

            // Suggest all the emojis!
            emojis.forEach { (emoji, description) ->
                newBuilder.suggest(emoji, LiteralMessage(description))
            }

            newBuilder.buildFuture()
        }

    CommandAPICommand("emoji")
        .withArguments(messageArgument)
        .executes(CommandExecutor { _, args ->
            Bukkit.broadcastMessage(args["message"] as String)
        })
        .register()
    // #endregion emojiCommandExample

    // #region commandArgumentsExampleStep1
    val commandSuggestions: ArgumentSuggestions<CommandSender> = ArgumentSuggestions { info, builder ->
        // The current argument, which is a full command
        val arg: String = info.currentArg()

        // Identify the position of the current argument
        var start = if (arg.contains(" ")) {
            // Current argument contains spaces - it starts after the last space and after the start of this argument.
            builder.start + arg.lastIndexOf(' ') + 1
        } else {
            // Input starts at the start of this argument
            builder.start
        }

        // Parse command using brigadier
        val parseResults: ParseResults<*> = Brigadier.getCommandDispatcher()
            .parse(info.currentArg(), Brigadier.getBrigadierSourceFromCommandSender(info.sender))

        // Intercept any parsing errors indicating an invalid command
        for ((_, exception) in parseResults.exceptions) {
            // Raise the error, with the cursor offset to line up with the argument
            throw CommandSyntaxException(exception.type, exception.rawMessage, exception.input, exception.cursor + start)
        }

        val completableFutureSuggestions: CompletableFuture<Suggestions> =
            Brigadier.getCommandDispatcher().getCompletionSuggestions(parseResults) as CompletableFuture<Suggestions>

        completableFutureSuggestions.thenApply { suggestions: Suggestions ->
            Suggestions(
                // Offset the index range of the suggestions by the start of the current argument
                StringRange(start, start + suggestions.range.length),
                // Copy the suggestions
                suggestions.list
            )
        }
    }
    // #endregion commandArgumentsExampleStep1

    // #region commandArgumentsExampleStep2
    CommandAPICommand("commandargument")
        .withArguments(GreedyStringArgument("command").replaceSuggestions(commandSuggestions))
        .executes(CommandExecutor { sender, args ->
            // Run the command using Bukkit.dispatchCommand()
            Bukkit.dispatchCommand(sender, args["command"] as String)
        })
        .register()
    // #endregion commandArgumentsExampleStep2
}