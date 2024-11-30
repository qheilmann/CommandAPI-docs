package createcommands.arguments.types.misc;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BiomeArgument;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;

class BiomeArguments {
    {
        // #region biomeArgumentsExample
        new CommandAPICommand("setbiome")
            .withArguments(new BiomeArgument("biome"))
            .executesPlayer((player, args) -> {
                Biome biome = (Biome) args.get("biome");

                Chunk chunk = player.getLocation().getChunk();
                player.getWorld().setBiome(chunk.getX(), player.getLocation().getBlockY(), chunk.getZ(), biome);
            })
            .register();
        // #endregion biomeArgumentsExample
    }
}
