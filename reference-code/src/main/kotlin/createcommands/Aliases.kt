package createcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandBlockCommandExecutor
import dev.jorel.commandapi.executors.EntityCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.commandBlockExecutor
import dev.jorel.commandapi.kotlindsl.entityExecutor

fun aliases() {
    // #region aliasesExample
    CommandAPICommand("getpos")
        // Declare your aliases
        .withAliases("getposition", "getloc", "getlocation", "whereami")

        // Declare your implementation
        .executesEntity(EntityCommandExecutor { entity, _ ->
            val loc = entity.location
            entity.sendMessage("You are at ${loc.blockX}, ${loc.blockY}, ${loc.blockZ}")
        })
        .executesCommandBlock(CommandBlockCommandExecutor { block, _ ->
            val loc = block.block.location
            block.sendMessage("You are at ${loc.blockX}, ${loc.blockY}, ${loc.blockZ}")
        })

        // Register the command
        .register()
    // #endregion aliasesExample
}

fun aliasesDSL() {
    // #region aliasesExampleDSL
    commandAPICommand("getpos") {
        // Declare your aliases
        withAliases("getposition", "getloc", "getlocation", "whereami")

        // Declare your implementation
        entityExecutor { entity, _ ->
            val loc = entity.location
            entity.sendMessage("You are at ${loc.blockX}, ${loc.blockY}, ${loc.blockZ}")
        }
        commandBlockExecutor { block, _ ->
            val loc = block.block.location
            block.sendMessage("You are at ${loc.blockX}, ${loc.blockY}, ${loc.blockZ}")
        }
    }
    // #endregion aliasesExampleDSL
}