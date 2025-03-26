package createcommands.arguments.types

import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.NBTCompoundArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.nbtCompoundArgument
import org.bukkit.plugin.java.JavaPlugin
import net.minecraft.nbt.CompoundTag
import com.saicone.rtag.Rtag
import com.saicone.rtag.tag.TagCompound

val nbtArguments = object : JavaPlugin() {
    // #region hookNbtAPIExample
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .initializeNBTAPI(ReadWriteNBT::class.java, NBT::wrapNMSTag)
        )
    }
    // #endregion hookNbtAPIExample
}

val nbtArgumentsMojang = object : JavaPlugin() {
    // #region hookNbtAPIExampleMojang
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .initializeNBTAPI(CompoundTag::class.java, { it as CompoundTag })
        )
    }
    // #endregion hookNbtAPIExampleMojang
}

val nbtArgumentsRtag = object : JavaPlugin() {
    // #region hookNbtAPIExampleRtag
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .initializeNBTAPI(Object::class.java, { it as Object })
        )
    }
    // #endregion hookNbtAPIExampleRtag
}

val nbtArgumentsRtagObjects = object : JavaPlugin() {
    // #region hookNbtAPIExampleRtagObjects
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .initializeNBTAPI(Map::class.java) { o -> TagCompound.getValue(Rtag.INSTANCE, o) }
        )
    }
    // #endregion hookNbtAPIExampleRtagObjects
}



fun nbtArguments() {
    // #region nbtCompoundArgumentsExampleMojang
    CommandAPICommand("award")
        .withArguments(NBTCompoundArgument<CompoundTag>("nbt"))
        .executes(CommandExecutor { _, args ->
            val nbt = args["nbt"] as CompoundTag
            // Do something with "nbt" here...
        })
        .register()
    // #endregion nbtCompoundArgumentsExampleMojang

    // #region nbtCompoundArgumentsExampleNBTAPI
    CommandAPICommand("award")
        .withArguments(NBTCompoundArgument<ReadWriteNBT>("nbt"))
        .executes(CommandExecutor { _, args ->
            val nbt = args["nbt"] as ReadWriteNBT
            // Do something with "nbt" here...
        })
        .register()
    // #endregion nbtCompoundArgumentsExampleNBTAPI

    // #region nbtCompoundArgumentsExampleRtagPath
    CommandAPICommand("test")
        .withArguments(NBTCompoundArgument<Any>("nbt"))
        .executes(CommandExecutor { _, args ->
            val result = Rtag.INSTANCE.get(args["nbt"], "some", "path", "here") as String
        })
        .register()
    // #endregion nbtCompoundArgumentsExampleRtagPath
}

fun nbtArgumentsDSL() {
    // #region nbtCompoundArgumentsExampleMojangDSL
    commandAPICommand("award") {
        nbtCompoundArgument<CompoundTag>("nbt")
        anyExecutor { _, args ->
            val nbt = args["nbt"] as CompoundTag
            // Do something with "nbt" here...
        }
    }
    // #endregion nbtCompoundArgumentsExampleMojangDSL

    // #region nbtCompoundArgumentsExampleNBTAPIDSL
    commandAPICommand("award") {
        nbtCompoundArgument<ReadWriteNBT>("nbt")
        anyExecutor { _, args ->
            val nbt = args["nbt"] as ReadWriteNBT
            // Do something with "nbt" here...
        }
    }
    // #endregion nbtCompoundArgumentsExampleNBTAPIDSL

    // #region nbtCompoundArgumentsExampleRtagPathDSL
    commandAPICommand("award") {
        nbtCompoundArgument<Any>("nbt")
        anyExecutor { _, args ->
            val result = Rtag.INSTANCE.get(args["nbt"], "some", "path", "here") as String
        }
    }
    // #endregion nbtCompoundArgumentsExampleRtagPathDSL
}