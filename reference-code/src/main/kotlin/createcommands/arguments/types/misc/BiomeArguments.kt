package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.BiomeArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.biomeArgument
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.block.Biome
import org.bukkit.entity.Player

fun biomeArguments() {
    // #region biomeArgumentsExample
    CommandAPICommand("setbiome")
        .withArguments(BiomeArgument("biome"))
        .executesPlayer(NormalExecutor<Player, Any> { player, args ->
            val biome = args["biome"] as Biome

            val chunk = player.location.chunk
            player.world.setBiome(chunk.x, player.location.blockY, chunk.z, biome)
        })
        .register()
    // #endregion biomeArgumentsExample
}

fun biomeArgumentsDSL() {
    // #region biomeArgumentsExampleDSL
    commandAPICommand("setbiome") {
        biomeArgument("biome")
        playerExecutor { player, args ->
            val biome = args["biome"] as Biome

            val chunk = player.location.chunk
            player.world.setBiome(chunk.x, player.location.blockY, chunk.z, biome)
        }
    }
    // #endregion biomeArgumentsExampleDSL
}