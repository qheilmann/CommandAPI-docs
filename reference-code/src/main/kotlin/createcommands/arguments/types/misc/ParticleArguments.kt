package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ParticleArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.particleArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.wrappers.ParticleData

fun particleArguments() {
    // #region withoutParticleDataExample
    CommandAPICommand("showparticle")
        .withArguments(ParticleArgument("particle"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val particleData = args["particle"] as ParticleData<Any>
            player.world.spawnParticle(particleData.particle(), player.location, 1)
        })
        .register()
    // #endregion withoutParticleDataExample

    // #region withParticleDataExample
    CommandAPICommand("showparticle")
        .withArguments(ParticleArgument("particle"))
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val particleData = args["particle"] as ParticleData<Any>
            player.world.spawnParticle(particleData.particle(), player.location, 1, particleData.data())
        })
        .register()
    // #endregion withParticleDataExample
}

fun particleArgumentsDSL() {
    // #region withoutParticleDataExampleDSL
    commandAPICommand("showparticle") {
        particleArgument("particle")
        playerExecutor { player, args ->
            val particleData = args["particle"] as ParticleData<Any>
            player.world.spawnParticle(particleData.particle(), player.location, 1)
        }
    }
    // #endregion withoutParticleDataExampleDSL

    // #region withParticleDataExampleDSL
    commandAPICommand("showparticle") {
        particleArgument("particle")
        playerExecutor { player, args ->
            val particleData = args["particle"] as ParticleData<Any>
            player.world.spawnParticle(particleData.particle(), player.location, 1, particleData.data())
        }
    }
    // #endregion withParticleDataExampleDSL
}