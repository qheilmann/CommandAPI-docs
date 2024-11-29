package createcommands.arguments.types.scoreboard

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ScoreHolderArgument
import dev.jorel.commandapi.arguments.ScoreboardSlotArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.scoreHolderArgumentMultiple
import dev.jorel.commandapi.kotlindsl.scoreboardSlotArgument
import dev.jorel.commandapi.wrappers.ScoreboardSlot
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun scoreboardArguments() {
    // #region scoreHolderArgumentExample
    CommandAPICommand("reward")
        // We want multiple players, so we use the ScoreHolderArgument.Multiple constructor
        .withArguments(ScoreHolderArgument.Multiple("players"))
        .executes(CommandExecutor { _, args ->
            // Get player names by casting to Collection<String>
            val players = args["players"] as Collection<String>

            for (playerName in players) {
                Bukkit.getPlayer(playerName)?.inventory!!.addItem(ItemStack(Material.DIAMOND, 3))
            }
        })
        .register()
    // #endregion scoreHolderArgumentExample

    // #region scoreboardSlotArgumentExample
    CommandAPICommand("clearobjectives")
        .withArguments(ScoreboardSlotArgument("slot"))
        .executes(CommandExecutor { _, args ->
            val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
            val slot = (args["slot"] as ScoreboardSlot).displaySlot
            scoreboard.clearSlot(slot)
        })
        .register()
    // #endregion scoreboardSlotArgumentExample
}

fun scoreboardArgumentsDSL() {
    // #region scoreHolderArgumentExampleDSL
    commandAPICommand("reward") {
        // We want multiple players, so we use the scoreHolderArgumentMultiple method
        scoreHolderArgumentMultiple("player")
        anyExecutor { _, args ->
            // Get player names by casting to Collection<String>
            val players = args["player"] as Collection<String>

            for (playerName in players) {
                Bukkit.getPlayer(playerName)?.inventory!!.addItem(ItemStack(Material.DIAMOND, 3))
            }
        }
    }
    // #endregion scoreHolderArgumentExampleDSL

    // #region scoreboardSlotArgumentExampleDSL
    commandAPICommand("clearobjectives") {
        scoreboardSlotArgument("slot")
        anyExecutor { _, args ->
            val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
            val slot = (args["slot"] as ScoreboardSlot).displaySlot
            scoreboard.clearSlot(slot)
        }
    }
    // #endregion scoreboardSlotArgumentExampleDSL
}