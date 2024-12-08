package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ParticleArgument;
import dev.jorel.commandapi.wrappers.ParticleData;

class ParticleArguments {
    static {
        // #region withoutParticleDataExample
        new CommandAPICommand("showparticle")
            .withArguments(new ParticleArgument("particle"))
            .executesPlayer((player, args) -> {
                ParticleData<?> particleData = (ParticleData<?>) args.get("particle");
                player.getWorld().spawnParticle(particleData.particle(), player.getLocation(), 1);
            })
            .register();
        // #endregion withoutParticleDataExample

        // #region withParticleDataExample
        new CommandAPICommand("showparticle")
            .withArguments(new ParticleArgument("particle"))
            .executesPlayer((player, args) -> {
                ParticleData<?> particleData = (ParticleData<?>) args.get("particle");
                player.getWorld().spawnParticle(particleData.particle(), player.getLocation(), 1, particleData.data());
            })
            .register();
        // #endregion withParticleDataExample
    }
}
