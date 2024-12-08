package createcommands.arguments

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.entity.Player

fun commandArguments() {
    // #region getArgExample
    CommandAPICommand("mycommand")
        .withArguments(StringArgument("name"))
        .withArguments(IntegerArgument("amount"))
        .withOptionalArguments(PlayerArgument("player"))
        .withOptionalArguments(PlayerArgument("target"))
        .withOptionalArguments(GreedyStringArgument("message"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val name = args[0] as String // Access arguments by index
            val amount = args["amount"] as Int // Access arguments by node name
            val p = args.getOrDefault("player", player) as Player // Access arguments using the getOrDefault(String, Object) method
            val target = args.getOrDefault("target") { player } as Player // Access arguments using the getOrDefault(String, Supplier<?>) method
            val message = args.getOptional("message").orElse("Hello!") as String // Access arguments using the getOptional(String) method
            // Do whatever with these values
        })
        .register()
    // #endregion getArgExample

    // #region getRawExample
    CommandAPICommand("mycommand")
        .withArguments(EntitySelectorArgument.ManyEntities("entities"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val entitySelector = args.getRaw("entities")!! // Access the raw argument with getRaw(String)
            // Do whatever with the entity selector
        })
        .register()
    // #endregion getRawExample

    // #region getUncheckedExample
    CommandAPICommand("mycommand")
        .withArguments(PlayerArgument("player"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val p: Player = args.getUnchecked("player")!!
            // Do whatever with the player
        })
        .register()
    // #endregion getUncheckedExample

    // #region getByArgumentExample
    val nameArgument = StringArgument("name")
    val amountArgument = IntegerArgument("amount")
    val playerArgument = PlayerArgument("player")
    val targetArgument = PlayerArgument("target")
    val messageArgument = GreedyStringArgument("message")

    CommandAPICommand("mycommand")
        .withArguments(nameArgument)
        .withArguments(amountArgument)
        .withOptionalArguments(playerArgument)
        .withOptionalArguments(targetArgument)
        .withOptionalArguments(messageArgument)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val name: String = args.getByArgument(nameArgument)!!
            val amount: Int = args.getByArgument(amountArgument)!!
            val p: Player = args.getByArgumentOrDefault(playerArgument, player)
            val target: Player = args.getByArgumentOrDefault(targetArgument, player)
            val message: String = args.getOptionalByArgument(messageArgument).orElse("Hello!")
            // Do whatever with these values
        })
        .register()
    // #endregion getByArgumentExample
}