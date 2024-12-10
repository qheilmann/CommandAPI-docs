package internal;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import dev.jorel.commandapi.Brigadier;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class BrigadierSuggestions {
    static {
        // #region emojiCommandExample
        Map<String, String> emojis = new HashMap<>();
        emojis.put("☻", "smile");
        emojis.put("❤", "heart");
        emojis.put("🔥", "fire");
        emojis.put("★", "star");
        emojis.put("☠", "death");
        emojis.put("⚠", "warning");
        emojis.put("☀", "sun");
        emojis.put("☺", "smile");
        emojis.put("☹", "frown");
        emojis.put("✉", "mail");
        emojis.put("☂", "umbrella");
        emojis.put("✘", "cross");
        emojis.put("♪", "music note (eighth)");
        emojis.put("♬", "music note (beamed sixteenth)");
        emojis.put("♩", "music note (quarter)");
        emojis.put("♫", "music note (beamed eighth)");
        emojis.put("☄", "comet");
        emojis.put("✦", "star");
        emojis.put("🗡", "sword");
        emojis.put("🪓", "axe");
        emojis.put("🔱", "trident");
        emojis.put("🎣", "fishing rod");
        emojis.put("🏹", "bow");
        emojis.put("⛏", "pickaxe");
        emojis.put("🍖", "food");

        Argument<String> messageArgument = new GreedyStringArgument("message")
            .replaceSuggestions((info, builder) -> {
                // Only display suggestions at the very end character
                builder = builder.createOffset(builder.getStart() + info.currentArg().length());

                // Suggest all the emojis!
                for (Entry<String, String> str : emojis.entrySet()) {
                    builder.suggest(str.getKey(), new LiteralMessage(str.getValue()));
                }

                return builder.buildFuture();
            });

        new CommandAPICommand("emoji")
            .withArguments(messageArgument)
            .executes((sender, args) -> {
                Bukkit.broadcastMessage((String) args.get("message"));
            })
            .register();
        // #endregion emojiCommandExample

        // #region commandArgumentsExampleStep1
        ArgumentSuggestions<CommandSender> commandSuggestions = (info, builder) -> {
            // The current argument, which is a full command
            String arg = info.currentArg();

            // Identify the position of the current argument
            int start;
            if (arg.contains(" ")) {
                // The Current argument contains spaces - it starts after the last space and after the start of this argument.
                start = builder.getStart() + arg.lastIndexOf(' ') + 1;
            } else {
                // Input starts at the start of this argument
                start = builder.getStart();
            }

            // Parse command using brigadier
            ParseResults<?> parseResults = Brigadier.getCommandDispatcher()
                .parse(info.currentArg(), Brigadier.getBrigadierSourceFromCommandSender(info.sender()));

            // Intercept any parsing errors indicating an invalid command
            if(!parseResults.getExceptions().isEmpty()) {
                CommandSyntaxException exception = parseResults.getExceptions().values().iterator().next();
                // Raise the error, with the cursor offset to line up with the argument
                throw new CommandSyntaxException(exception.getType(), exception.getRawMessage(), exception.getInput(), exception.getCursor() + start);
            }

            return Brigadier
                .getCommandDispatcher()
                .getCompletionSuggestions(parseResults)
                .thenApply(suggestionsObject -> {
                    // Brigadier's suggestions
                    Suggestions suggestions = (Suggestions) suggestionsObject;

                    return new Suggestions(
                        // Offset the index range of the suggestions by the start of the current argument
                        new StringRange(start, start + suggestions.getRange().getLength()),
                        // Copy the suggestions
                        suggestions.getList()
                    );
                });
        };
        // #endregion commandArgumentsExampleStep1

        // #region commandArgumentsExampleStep2
        new CommandAPICommand("commandargument")
            .withArguments(new GreedyStringArgument("command").replaceSuggestions(commandSuggestions))
            .executes((sender, args) -> {
                // Run the command using Bukkit.dispatchCommand()
                Bukkit.dispatchCommand(sender, (String) args.get("command"));
            })
            .register();
        // #endregion commandArgumentsExampleStep2
    }
}
