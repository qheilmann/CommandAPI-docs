package createcommands.executors;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;

import java.util.Arrays;

class HandleFailures {
    static {
        // #region handleFailuresExample
        // Array of fruit
        String[] fruit = new String[]{"banana", "apple", "orange"};

        // Register the command
        new CommandAPICommand("getfruit")
            .withArguments(new StringArgument("item").replaceSuggestions(ArgumentSuggestions.strings(fruit)))
            .executes((sender, args) -> {
                String inputFruit = (String) args.get("item");

                if (Arrays.asList(fruit).contains(inputFruit)) {
                    // Do something with inputFruit
                } else {
                    // The sender's input is not in the list of fruit
                    throw CommandAPI.failWithString("That fruit doesn't exist!");
                }
            })
            .register();
        // #endregion handleFailuresExample
    }
}
