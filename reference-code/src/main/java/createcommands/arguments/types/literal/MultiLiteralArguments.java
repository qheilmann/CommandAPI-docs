package createcommands.arguments.types.literal;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import org.bukkit.GameMode;

class MultiLiteralArguments {
    {
        // #region multiliteralArgumentsExample
        new CommandAPICommand("gamemode")
            .withArguments(new MultiLiteralArgument("gamemodes", "adventure", "creative", "spectator", "survival"))
            .executesPlayer((player, args) -> {
                // The literal string that the player enters IS available in the args[]
                switch ((String) args.get("gamemodes")) {
                    case "adventure":
                        player.setGameMode(GameMode.ADVENTURE);
                        break;
                    case "creative":
                        player.setGameMode(GameMode.CREATIVE);
                        break;
                    case "spectator":
                        player.setGameMode(GameMode.SPECTATOR);
                        break;
                    case "survival":
                        player.setGameMode(GameMode.SURVIVAL);
                        break;
                    default:
                        player.sendMessage("Invalid gamemode: " + args.get("gamemodes"));
                        break;
                }
            })
            .register();
        // #endregion multiliteralArgumentsExample
    }
}
