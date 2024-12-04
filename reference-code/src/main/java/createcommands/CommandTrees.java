package createcommands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

class CommandTrees {
    {
        // #region commandTreesExample
        new CommandTree("sayhi")
            .executes((sender, args) -> {
                sender.sendMessage("Hi!");
            })
            .then(new PlayerArgument("target")
                .executes((sender, args) -> {
                    Player target = (Player) args.get("target");
                    target.sendMessage("Hi");
                }))
            .register();
        // #endregion commandTreesExample

        // #region legacyLargeArgumentsExample
        new CommandTree("example")
            .then(new LiteralArgument("arg1")
                .then(new StringArgument("arg2")
                    .then(new StringArgument("arg3")
                        .then(new DoubleArgument("arg4", 0)
                            .then(new StringArgument("arg5")
                                .executes((sender, args) -> {
                                    // your code here
                                }))))))
            .register();
        // #endregion legacyLargeArgumentsExample

        // #region nestedLargeArgumentsExample
        new CommandTree("example")
            .thenNested(
                new LiteralArgument("arg1"),
                new StringArgument("arg2"),
                new StringArgument("arg3"),
                new DoubleArgument("arg4", 0),
                new StringArgument("arg5")
                    .executes((sender, args) -> {
                        // your code here
                    })
            ).register();
        // #endregion nestedLargeArgumentsExample

        new JavaPlugin() {
            @Override
            public void onEnable() {
                // #region signeditCommandExample
                new CommandTree("signedit")
                    .then(new LiteralArgument("set")
                        .then(new IntegerArgument("line_number", 1, 4)
                            .then(new GreedyStringArgument("text")
                                .executesPlayer((player, args) -> {
                                    // /signedit set <line_number> <text>
                                    Sign sign = getTargetSign(player);
                                    int lineNumber = (int) args.get("line_number");
                                    String text = (String) args.get("text");
                                    sign.setLine(lineNumber - 1, text);
                                    sign.update(true);
                                }))))
                    .then(new LiteralArgument("clear")
                        .then(new IntegerArgument("line_number", 1, 4)
                            .executesPlayer((player, args) -> {
                                // /signedit clear <line_number>
                                Sign sign = getTargetSign(player);
                                int lineNumber = (int) args.get("line_number");
                                sign.setLine(lineNumber - 1, "");
                                sign.update(true);
                            })))
                    .then(new LiteralArgument("copy")
                        .then(new IntegerArgument("line_number", 1, 4)
                            .executesPlayer((player, args) -> {
                                // /signedit copy <line_number>
                                Sign sign = getTargetSign(player);
                                int lineNumber = (int) args.get("line_number");
                                player.setMetadata("copied_sign_text", new FixedMetadataValue(this, sign.getLine(lineNumber - 1)));
                            })))
                    .then(new LiteralArgument("paste")
                        .then(new IntegerArgument("line_number", 1, 4)
                            .executesPlayer((player, args) -> {
                                // /signedit copy <line_number>
                                Sign sign = getTargetSign(player);
                                int lineNumber = (int) args.get("line_number");
                                sign.setLine(lineNumber - 1, player.getMetadata("copied_sign_text").get(0).asString());
                                sign.update(true);
                            })))
                    .register();
                // #endregion signeditCommandExample
            }
        };
    }

    public Sign getTargetSign(@NotNull Player player) throws WrapperCommandSyntaxException {
        Block block = player.getTargetBlock(null, 256);
        if (block.getState() instanceof Sign sign) {
            return sign;
        } else {
            throw CommandAPI.failWithString("You're not looking at a sign!");
        }
    }
}
