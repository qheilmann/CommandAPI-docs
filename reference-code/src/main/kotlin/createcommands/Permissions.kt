package createcommands

import createcommands.Permissions.Economy
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.DoubleArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.entity.Player

fun permissions() {
    // #region withPermissionExample
    // Register the /god command with the permission node "command.god"
    CommandAPICommand("god")
        .withPermission(CommandPermission.fromString("command.god"))
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.isInvulnerable = true
        })
        .register()
    // #endregion withPermissionExample

    // #region withStringPermissionExample
    // Register the /god command with the permission node "command.god", without creating a CommandPermission
    CommandAPICommand("god")
        .withPermission("command.god")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.isInvulnerable = true
            player.sendMessage("God mode enabled")
        })
        .register()
    // #endregion withStringPermissionExample

    // #region argumentPermissionExampleStep1
    // Register /kill command normally. Since no permissions are applied, anyone can run this command
    CommandAPICommand("kill")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            player.health = 0.0
            player.sendMessage("God mode enabled")
        })
        .register()
    // #endregion argumentPermissionExampleStep1

    // #region argumentPermissionExampleStep2
    // Adds the OP permission to the "target" argument. The sender requires OP to execute /kill <target>
    CommandAPICommand("kill")
        .withArguments(PlayerArgument("target").withPermission(CommandPermission.OP))
        .executesPlayer(PlayerCommandExecutor { _, args ->
            (args["target"] as Player).health = 0.0
        })
        .register()
    // #endregion argumentPermissionExampleStep2

    // #region childBasedPermissionExample
    // /economy - requires the permission "economy.self" to exectue
    CommandAPICommand("economy")
        .withPermission("economy.self")
        .executesPlayer(PlayerCommandExecutor { player, _ ->
            // send the executor their own balance here.
        })
        .register()

    // /economy <target> - requires the permission "economy.other" to execute
    CommandAPICommand("economy")
        .withPermission("economy.other") // The important part of this example
        .withArguments(PlayerArgument("target"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["target"] as Player

            // send the executor the target's balance
            player.sendMessage(target.name + "'s balance: " + Economy.getBalance(target))
        })
        .register()

    // /economy give <target> <amount> - requires the permission "economy.admin.give" to execute
    CommandAPICommand("economy")
        .withPermission("economy.admin.give") // The important part of this example
        .withArguments(PlayerArgument("target"))
        .withArguments(DoubleArgument("amount"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["target"] as Player
            val amount = args["amount"] as Double

            // update the target's balance
            Economy.updateBalance(target, amount)
        })
        .register()

    // /economy reset <target> - requires the permission "economy.admin.reset" to execute
    CommandAPICommand("economy")
        .withPermission("economy.admin.reset") // The important part of this example
        .withArguments(PlayerArgument("target"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["target"] as Player

            // reset the target's balance
            Economy.resetBalance(target)
        })
        .register()
    // #endregion childBasedPermissionExample
}