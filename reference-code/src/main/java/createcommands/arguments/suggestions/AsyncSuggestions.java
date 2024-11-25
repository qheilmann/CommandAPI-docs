package createcommands.arguments.suggestions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

class AsyncSuggestions {
    {
        JavaPlugin plugin = null;
        // #region asyncSuggestionsExample
        new CommandAPICommand("setconfig")
            .withArguments(new StringArgument("key")
                .replaceSuggestions(ArgumentSuggestions.stringsAsync(info ->
                    CompletableFuture.supplyAsync(() ->
                        plugin.getConfig().getKeys(false).toArray(new String[0])
                    )
                ))
            )
            .withArguments(new TextArgument("value"))
            .executes((sender, args) -> {
                String key = (String) args.get("key");
                String value = (String) args.get("value");
                plugin.getConfig().set(key, value);
            })
            .register();
        // #endregion asyncSuggestionsExample
    }
}
