package createcommands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;

class Subcommands {
    static {
        // #region subcommandsExampleStep1
        CommandAPICommand groupAdd = new CommandAPICommand("add")
            .withArguments(new StringArgument("permission"))
            .withArguments(new StringArgument("groupName"))
            .executes((sender, args) -> {
                // perm group add code
            });
        // #endregion subcommandsExampleStep1

        // #region subcommandsExampleStep2
        CommandAPICommand groupRemove = new CommandAPICommand("remove")
            .withArguments(new StringArgument("permission"))
            .withArguments(new StringArgument("groupName"))
            .executes((sender, args) -> {
                // perm group remove code
            });

        CommandAPICommand group = new CommandAPICommand("group")
            .withSubcommand(groupAdd)
            .withSubcommand(groupRemove);
        // #endregion subcommandsExampleStep2

        // #region subcommandsExampleStep3
        new CommandAPICommand("perm")
            .withSubcommand(group)
            .register();
        // #endregion subcommandsExampleStep3

        // #region subcommandsFullExample
        new CommandAPICommand("perm")
            .withSubcommand(new CommandAPICommand("group")
                .withSubcommand(new CommandAPICommand("add")
                    .withArguments(new StringArgument("permission"))
                    .withArguments(new StringArgument("groupName"))
                    .executes((sender, args) -> {
                        // perm group add code
                    })
                )
                .withSubcommand(new CommandAPICommand("remove")
                    .withArguments(new StringArgument("permission"))
                    .withArguments(new StringArgument("groupName"))
                    .executes((sender, args) -> {
                        // perm group remove code
                    })
                )
            )
            .withSubcommand(new CommandAPICommand("user")
                .withSubcommand(new CommandAPICommand("add")
                    .withArguments(new StringArgument("permission"))
                    .withArguments(new StringArgument("userName"))
                    .executes((sender, args) -> {
                        // perm user add code
                    })
                )
                .withSubcommand(new CommandAPICommand("remove")
                    .withArguments(new StringArgument("permission"))
                    .withArguments(new StringArgument("userName"))
                    .executes((sender, args) -> {
                        // perm user remove code
                    })
                )
            )
            .register();
        // #endregion subcommandsFullExample
    }
}
