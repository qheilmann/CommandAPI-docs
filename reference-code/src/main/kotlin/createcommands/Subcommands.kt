package createcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.stringArgument
import dev.jorel.commandapi.kotlindsl.subcommand

fun subcommands() {
    // #region subcommandsExampleStep1
    val groupAdd = CommandAPICommand("add")
        .withArguments(StringArgument("permission"))
        .withArguments(StringArgument("groupName"))
        .executes(CommandExecutor { sender, args ->
            // perm group add code
        })
    // #endregion subcommandsExampleStep1

    // #region subcommandsExampleStep2
    val groupRemove = CommandAPICommand("remove")
        .withArguments(StringArgument("permission"))
        .withArguments(StringArgument("groupName"))
        .executes(CommandExecutor { sender, args ->
            // perm group remove code
        })

    val group = CommandAPICommand("group")
        .withSubcommand(groupAdd)
        .withSubcommand(groupRemove)
    // #endregion subcommandsExampleStep2

    // #region subcommandsExampleStep3
    CommandAPICommand("perm")
        .withSubcommand(group)
        .register()
    // #endregion subcommandsExampleStep3

    // #region subcommandsFullExample
    CommandAPICommand("perm")
        .withSubcommand(CommandAPICommand("group")
            .withSubcommand(CommandAPICommand("add")
                .withArguments(StringArgument("permission"))
                .withArguments(StringArgument("groupName"))
                .executes(CommandExecutor { sender, args ->
                    // perm group add code
                })
            )
            .withSubcommand(CommandAPICommand("remove")
                .withArguments(StringArgument("permission"))
                .withArguments(StringArgument("groupName"))
                .executes(CommandExecutor { sender, args ->
                    // perm group remove code
                })
            )
        )
        .withSubcommand(CommandAPICommand("user")
            .withSubcommand(CommandAPICommand("add")
                .withArguments(StringArgument("permission"))
                .withArguments(StringArgument("userName"))
                .executes(CommandExecutor { sender, args ->
                    // perm user adds code
                })
            )
            .withSubcommand(CommandAPICommand("remove")
                .withArguments(StringArgument("permission"))
                .withArguments(StringArgument("userName"))
                .executes(CommandExecutor { sender, args ->
                    // perm user remove code
                })
            )
        )
        .register()
    // #endregion subcommandsFullExample
}

fun subcommandsDSL() {
    // #region subcommandsExampleStep1DSL
    val groupAdd = subcommand("add") {
        stringArgument("permission")
        stringArgument("groupName")
        anyExecutor { sender, args ->
            // perm group add code
        }
    }
    // #endregion subcommandsExampleStep1DSL

    // #region subcommandsExampleStep2DSL
    val groupRemove = subcommand("remove") {
        stringArgument("permission")
        stringArgument("groupName")
        anyExecutor { sender, args ->
            // perm group remove code
        }
    }

    val group = subcommand("group") {
        subcommand(groupAdd)
        subcommand(groupRemove)
    }
    // #endregion subcommandsExampleStep2DSL

    // #region subcommandsExampleStep3DSL
    commandAPICommand("perm") {
        subcommand(group)
    }
    // #endregion subcommandsExampleStep3DSL

    // #region subcommandsFullExampleDSL
    commandAPICommand("perm") {
        subcommand("group") {
            subcommand("add") {
                stringArgument("permission")
                stringArgument("groupName")
                anyExecutor { sender, args ->
                    // perm group add code
                }
            }
            subcommand("remove") {
                stringArgument("permission")
                stringArgument("groupName")
                anyExecutor { sender, args ->
                    // perm group remove code
                }
            }
        }
        subcommand("user") {
            subcommand("add") {
                stringArgument("permission")
                stringArgument("userName")
                anyExecutor { sender, args ->
                    // perm user add code
                }
            }
            subcommand("remove") {
                stringArgument("permission")
                stringArgument("userName")
                anyExecutor { sender, args ->
                    // perm user remove code
                }
            }
        }
    }
    // #endregion subcommandsFullExampleDSL
}