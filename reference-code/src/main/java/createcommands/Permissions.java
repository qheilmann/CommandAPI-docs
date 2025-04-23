package createcommands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

class Permissions {
    static {
        // #region withPermissionExample
        // Register the /god command with the permission node "command.god"
        new CommandAPICommand("god")
            .withPermission(CommandPermission.fromString("command.god"))
            .executesPlayer((player, args) -> {
                player.setInvulnerable(true);
                player.sendMessage("God mode enabled");
            })
            .register();
        // #endregion withPermissionExample

        // #region withStringPermissionExample
        // Register the /god command with the permission node "command.god", without creating a CommandPermission
        new CommandAPICommand("god")
            .withPermission("command.god")
            .executesPlayer((player, args) -> {
                player.setInvulnerable(true);
                player.sendMessage("God mode enabled");
            })
            .register();
        // #endregion withStringPermissionExample

        // #region argumentPermissionExampleStep1
        // Register /kill command normally. Since no permissions are applied, anyone can run this command
        new CommandAPICommand("kill")
            .executesPlayer((player, args) -> {
                player.setHealth(0);
            })
            .register();
        // #endregion argumentPermissionExampleStep1

        // #region argumentPermissionExampleStep2
        // Adds the OP permission to the "target" argument. The sender requires OP to execute /kill <target>
        new CommandAPICommand("kill")
            .withArguments(new PlayerArgument("target").withPermission(CommandPermission.OP))
            .executesPlayer((player, args) -> {
                ((Player) args.get("target")).setHealth(0);
            })
            .register();
        // #endregion argumentPermissionExampleStep2

        // #region childBasedPermissionExample
        // /economy - requires the permission "economy.self" to execute
        new CommandAPICommand("economy")
            .withPermission("economy.self") // The important part of this example
            .executesPlayer((player, args) -> {
                // send the executor their own balance here.
            })
            .register();

        // /economy <target> - requires the permission "economy.other" to execute
        new CommandAPICommand("economy")
            .withPermission("economy.other") // The important part of this example
            .withArguments(new PlayerArgument("target"))
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");

                // Send a message to the executor with the target's balance
                player.sendMessage(target.getName() + "'s balance: " + Economy.getBalance(target));
            })
            .register();

        // /economy give <target> <amount> - requires the permission "economy.admin.give" to execute
        new CommandAPICommand("economy")
            .withPermission("economy.admin.give") // The important part of this example
            .withArguments(new LiteralArgument("give"))
            .withArguments(new PlayerArgument("target"))
            .withArguments(new DoubleArgument("amount"))
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");
                double amount = (Double) args.get("amount");

                // Update the target player's balance
                Economy.updateBalance(target, amount);
            })
            .register();

        // /economy reset <target> - requires the permission "economy.admin.reset" to execute
        new CommandAPICommand("economy")
            .withPermission("economy.admin.reset") // The important part of this example
            .withArguments(new LiteralArgument("reset"))
            .withArguments(new PlayerArgument("target"))
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("target");

                // Reset target balance
                Economy.resetBalance(target);
            })
            .register();
        // #endregion childBasedPermissionExample
    }

    static class Economy {
        public static double getBalance(Player player) {
            return 0;
        }

        public static void updateBalance(Player player, double amount) {
        }

        public static void resetBalance(Player player) {
        }
    }
}
