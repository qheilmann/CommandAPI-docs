package createcommands.arguments

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.PotionEffectArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.arguments
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.stringArgument
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.potion.PotionEffectType

fun arguments() {
    // #region registerArgumentsExample
    CommandAPICommand("mycommand")
        .withArguments(StringArgument("arg0"))
        .withArguments(StringArgument("arg1"))
        .withArguments(StringArgument("arg2"))
        // And so on
    // #endregion registerArgumentsExample

    // #region registerArgumentsVarargExample
    CommandAPICommand("mycommand")
        .withArguments(
            StringArgument("arg0"),
            StringArgument("arg1"),
            StringArgument("arg2")
        )
        // And so on
    // #endregion registerArgumentsVarargExample

    // #region registerArgumentsListExample
    val arguments = listOf(
        StringArgument("arg0"),
        StringArgument("arg1"),
        StringArgument("arg2")
    )

    CommandAPICommand("mycommand")
        .withArguments(arguments)
        // And so on
    // #endregion registerArgumentsListExample

    // #region argumentCastExample
    val commandArguments = listOf(
        StringArgument("arg0"),
        PotionEffectArgument("arg1"),
        LocationArgument("arg2")
    )

    CommandAPICommand("cmd")
        .withArguments(commandArguments)
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val stringArg = args["arg0"] as String
            val potionArg = args["arg1"] as PotionEffectType
            val locationArg = args["arg2"] as Location
        })
        .register()
    // #endregion argumentCastExample
}

fun argumentsDSL() {
    // #region registerArgumentsExampleDSL
    commandAPICommand("mycommand") {
        stringArgument("arg0")
        stringArgument("arg1")
        stringArgument("arg2")
        // And so on
    }
    // #endregion registerArgumentsExampleDSL

    // #region registerArgumentsVarargExampleDSL
    commandAPICommand("mycommand") {
        arguments(
            StringArgument("arg0"),
            StringArgument("arg1"),
            StringArgument("arg2")
        )
        // And so on
    }
    // #endregion registerArgumentsVarargExampleDSL

    // #region registerArgumentsListExampleDSL
    val arguments = listOf(
        StringArgument("arg0"),
        StringArgument("arg1"),
        StringArgument("arg2")
    )

    commandAPICommand("mycommand") {
        arguments(*arguments.toTypedArray())
        // And so on
    }
    // #endregion registerArgumentsListExampleDSL

    // #region argumentCastExampleDSL
    val args = listOf(
        StringArgument("arg0"),
        PotionEffectArgument("arg1"),
        LocationArgument("arg2")
    )

    commandAPICommand("cmd") {
        arguments(*args.toTypedArray())
        anyExecutor { _, args ->
            val stringArg = args["arg0"] as String
            val potionArg = args["arg1"] as PotionEffectType
            val locationArg = args["arg2"] as Location
        }
    }
    // #endregion argumentCastExampleDSL
}