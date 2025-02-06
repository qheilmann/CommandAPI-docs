package createcommands.arguments.types.position

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.locationArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player

fun locationArguments() {
    // #region centerPositionExample
    LocationArgument("location", LocationType.PRECISE_POSITION, true)
    // #endregion centerPositionExample

    // #region doNotCenterPositionExample
    LocationArgument("location", LocationType.PRECISE_POSITION, false)
    // #endregion doNotCenterPositionExample

    // #region breakCommandExample
    CommandAPICommand("break")
        // We want to focus on blocks in particular, so use BLOCK_POSITION
        .withArguments(LocationArgument("block", LocationType.BLOCK_POSITION))
        .executesPlayer(NormalExecutor<Player, Any> { _, args ->
            (args["block"] as Location).block.type = Material.AIR
        })
        .register()
    // #endregion breakCommandExample

}

fun locationArgumentsDSL() {
    // #region breakCommandExampleDSL
    commandAPICommand("break") {
        // We want to focus on blocks in particular, so use BLOCK_POSITION
        locationArgument("block", LocationType.BLOCK_POSITION)
        playerExecutor { _, args ->
            (args["block"] as Location).block.type = Material.AIR
        }
    }
    // #endregion breakCommandExampleDSL
}