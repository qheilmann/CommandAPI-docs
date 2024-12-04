package createcommands

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandTree
import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.block.Sign
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin

fun commandTrees() {
    // #region commandTreesExample
    CommandTree("sayhi")
        .executes(CommandExecutor { sender, _ ->
            sender.sendMessage("Hi!")
        })
        .then(
            PlayerArgument("target")
                .executes(CommandExecutor { _, args ->
                    val target = args["target"] as Player
                    target.sendMessage("Hi")
                })
        )
        .register()
    // #endregion commandTreesExample

    // @formatter:off
    // #region legacyLargeArgumentsExample
    CommandTree("example")
        .then(LiteralArgument("arg1")
            .then(StringArgument("arg2")
                .then(StringArgument("arg3")
                    .then(DoubleArgument("arg4", 0.0)
                        .then(StringArgument("arg5")
                            .executes(CommandExecutor { sender, _ ->
                                // your code here
                            }))))))
        .register()
    // #endregion legacyLargeArgumentsExample
    // @formatter:on

    // #region nestedLargeArgumentsExample
    CommandTree("example")
        .thenNested(
            LiteralArgument("arg1"),
            StringArgument("arg2"),
            StringArgument("arg3"),
            DoubleArgument("arg4", 0.0),
            StringArgument("arg5")
                .executes(CommandExecutor { sender, _ ->
                    // your code here
                })
        ).register()
    // #endregion nestedLargeArgumentsExample

    object : JavaPlugin() {
        override fun onEnable() {
            // @formatter:off
            // #region signeditCommandExample
            CommandTree("signedit")
                .then(LiteralArgument("set")
                    .then(IntegerArgument("line_number", 1, 4)
                        .then(GreedyStringArgument("text")
                            .executesPlayer(PlayerCommandExecutor { player, args ->
                                // /signedit set <line_number> <text>
                                val sign: Sign = getTargetSign(player)
                                val line_number = args["line_number"] as Int
                                val text = args["text"] as String
                                sign.setLine(line_number - 1, text)
                                sign.update(true)
                            }))))
                .then(LiteralArgument("clear")
                    .then(IntegerArgument("line_number", 1, 4)
                        .executesPlayer(PlayerCommandExecutor { player, args ->
                            // /signedit clear <line_number>
                            val sign: Sign = getTargetSign(player)
                            val line_number = args["line_number"] as Int
                            sign.setLine(line_number - 1, "")
                            sign.update(true)
                        })))
                .then(LiteralArgument("copy")
                    .then(IntegerArgument("line_number", 1, 4)
                        .executesPlayer(PlayerCommandExecutor { player, args ->
                            // /signedit copy <line_number>
                            val sign: Sign = getTargetSign(player)
                            val line_number = args["line_number"] as Int
                            player.setMetadata("copied_sign_text", FixedMetadataValue(this, sign.getLine(line_number - 1)))
                        })))
                .then(LiteralArgument("paste")
                    .then(IntegerArgument("line_number", 1, 4)
                        .executesPlayer(PlayerCommandExecutor { player, args ->
                            // /signedit copy <line_number>
                            val sign: Sign = getTargetSign(player)
                            val line_number = args["line_number"] as Int
                            sign.setLine(line_number - 1, player.getMetadata("copied_sign_text")[0].asString())
                            sign.update(true)
                        })))
                .register()
            // #endregion signeditCommandExample
            // @formatter:on
        }

        private fun getTargetSign(player: Player): Sign = player.getTargetBlock(null, 256).state as? Sign ?: throw CommandAPI.failWithString("You're not looking at a sign!")
    }
}

fun commandTreesDSL() {
    // #region legacyLargeArgumentsExampleDSL
    commandTree("example") {
        literalArgument("arg1") {
            stringArgument("arg2") {
                stringArgument("arg3") {
                    doubleArgument("arg4", 0.0) {
                        stringArgument("arg5") {
                            anyExecutor { sender, _ ->
                                // your code here
                            }
                        }
                    }
                }
            }
        }
    }
    // #endregion legacyLargeArgumentsExampleDSL

    // #region nestedLargeArgumentsExampleDSL
    commandTree("example") {
        nested {
            literalArgument("arg1")
            stringArgument("arg2")
            stringArgument("arg3")
            doubleArgument("arg4", 0.0)
            stringArgument("arg5") {
                anyExecutor { sender, _ ->
                    // your code here
                }
            }
        }
    }
    // or
    commandTree("example") {
        nestedArguments(
            LiteralArgument("arg1"),
            StringArgument("arg2"),
            StringArgument("arg3"),
            DoubleArgument("arg4", 0.0),
            StringArgument("arg5")
        ) {
            anyExecutor { sender, _ ->
                // your code here
            }
        }
    }
    // #endregion nestedLargeArgumentsExampleDSL
}