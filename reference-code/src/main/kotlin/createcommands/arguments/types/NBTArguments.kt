package createcommands.arguments.types

import de.tr7zw.changeme.nbtapi.NBTContainer
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.NBTCompoundArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.nbtCompoundArgument
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

val nbtArguments = object : JavaPlugin() {
    // #region hookNbtAPIExample
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .initializeNBTAPI(NBTContainer::class.java, ::NBTContainer)
        )
    }
    // #endregion hookNbtAPIExample
}

fun nbtArguments() {
    // #region nbtCompoundArgumentsExample
    CommandAPICommand("award")
        .withArguments(NBTCompoundArgument<NBTContainer>("nbt"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val nbt = args["nbt"] as NBTContainer
            // Do something with "nbt" here...
        })
        .register()
    // #endregion nbtCompoundArgumentsExample
}

fun nbtArgumentsDSL() {
    // #region nbtCompoundArgumentsExampleDSL
    commandAPICommand("award") {
        nbtCompoundArgument<NBTContainer>("nbt")
        anyExecutor { _, args ->
            val nbt = args["nbt"] as NBTContainer
            // Do something with "nbt" here...
        }
    }
    // #endregion nbtCompoundArgumentsExampleDSL
}