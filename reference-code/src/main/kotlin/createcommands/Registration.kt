package createcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.Bukkit

fun registrationExample() {
    // #region registrationExample
    // Create our command
    CommandAPICommand("broadcastmsg")
        .withArguments(GreedyStringArgument("message")) // The arguments
        .withAliases("broadcast", "broadcastmessage")   // Command aliases
        .withPermission(CommandPermission.OP)           // Required permissions
        .executes(CommandExecutor { sender, args ->
            val message = args["message"] as String
            Bukkit.getServer().broadcastMessage(message)
        })
        .register()
    // #endregion registrationExample
}