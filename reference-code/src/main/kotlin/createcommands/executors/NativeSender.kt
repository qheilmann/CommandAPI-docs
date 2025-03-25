package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.CommandArgument
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.WorldArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.NativeCommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import dev.jorel.commandapi.wrappers.CommandResult
import dev.jorel.commandapi.wrappers.NativeProxyCommandSender
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.CommandSender

fun nativeSender() {
    // #region breakCommandExample
    CommandAPICommand("break")
        .executesNative(NativeCommandExecutor { sender, _ ->
            val location = sender.location
            location.block.breakNaturally()
        })
        .register()
    // #endregion breakCommandExample

    // #region constructorExample
    CommandAPICommand("executeAs")
        .withArguments(
            EntitySelectorArgument.OneEntity("target"),
            LocationArgument("location"),
            WorldArgument("world"),
            CommandArgument("command")
        )
        .executes(CommandExecutor { caller, args ->
            val callee = args["target"] as CommandSender
            val location = args["location"] as Location
            val world = args["world"] as World
            val command = args["command"] as CommandResult

            command.execute(NativeProxyCommandSender.from(caller, callee, location, world))
        })
        .register()
    // #endregion constructorExample
}

fun nativeSenderDSL() {
    // #region breakCommandExampleDSL
    commandAPICommand("break") {
        nativeExecutor { sender, _ ->
            val location = sender.location
            location.block.breakNaturally()
        }
    }
    // #endregion breakCommandExampleDSL

    // #region constructorExampleDSL
    commandAPICommand("executeAs") {
        entitySelectorArgumentOneEntity("target")
        locationArgument("location")
        worldArgument("world")
        commandArgument("command")
        anyExecutor { caller, args ->
            val callee = args["target"] as CommandSender
            val location = args["location"] as Location
            val world = args["world"] as World
            val command = args["command"] as CommandResult

            command.execute(NativeProxyCommandSender.from(caller, callee, location, world))
        }
    }
    // #endregion constructorExampleDSL
}