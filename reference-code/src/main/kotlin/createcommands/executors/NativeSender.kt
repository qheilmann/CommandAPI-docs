package createcommands.executors

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.NativeCommandExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.nativeExecutor

fun nativeSender() {
    // #region breakCommandExample
    CommandAPICommand("break")
        .executesNative(NativeCommandExecutor { sender, _ ->
            val location = sender.location
            location.block.breakNaturally()
        })
        .register()
    // #endregion breakCommandExample
}

fun nativeSenderDSL() {
    // #region breakCommandExampleDSL
    commandAPICommand("break") {
        nativeExecutor { sender, _ ->
            val location = sender.location
            location.block.breakNaturally()
        }
    }
    // #endregion breakCommandExampleDSL
}