package createcommands.arguments.types;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import org.bukkit.plugin.java.JavaPlugin;

class PrimitiveArguments {
    {
        new JavaPlugin() {
            @Override
            public void onEnable() {
                // #region primitiveArgumentsExample
                // Load keys from a config file
                String[] configKeys = getConfig().getKeys(true).toArray(new String[0]);

                // Register our command
                new CommandAPICommand("editconfig")
                    .withArguments(new TextArgument("config-key").replaceSuggestions(ArgumentSuggestions.strings(info -> configKeys)))
                    .withArguments(new BooleanArgument("value"))
                    .executes((sender, args) -> {
                        // Update the config with the boolean argument
                        getConfig().set((String) args.get("config-key"), (boolean) args.get("value"));
                    })
                    .register();
                // #endregion primitiveArgumentsExample
            }
        };
    }
}
