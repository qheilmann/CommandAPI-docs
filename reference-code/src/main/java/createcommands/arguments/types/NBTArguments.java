package createcommands.arguments.types;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.NBTCompoundArgument;

import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;
import net.minecraft.nbt.CompoundTag;
import com.saicone.rtag.Rtag;
import com.saicone.rtag.tag.TagCompound;

class NBTArguments {
    {
        new JavaPlugin() {
            // #region hookNbtAPIExample
            @Override
            public void onLoad() {
                CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                    .initializeNBTAPI(ReadWriteNBT.class, NBT::wrapNMSTag)
                );
            }
            // #endregion hookNbtAPIExample
        };

        new JavaPlugin() {
            // #region hookNbtAPIExampleMojang
            @Override
            public void onLoad() {
                CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                    .initializeNBTAPI(CompoundTag.class, o -> o)
                );
            }
            // #endregion hookNbtAPIExampleMojang
        };

        new JavaPlugin() {
            // #region hookNbtAPIExampleRtag
            @Override
            public void onLoad() {
                CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                    .initializeNBTAPI(Object.class, o -> o)
                );
            }
            // #endregion hookNbtAPIExampleRtag
        };

        new JavaPlugin() {
            // #region hookNbtAPIExampleRtagObjects
            @Override
            public void onLoad() {
                CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                    .initializeNBTAPI(Map.class, o -> TagCompound.getValue(Rtag.INSTANCE, o))
                );
            }
            // #endregion hookNbtAPIExampleRtagObjects
        };

        // #region nbtCompoundArgumentsExampleMojang
        new CommandAPICommand("award")
            .withArguments(new NBTCompoundArgument<CompoundTag>("nbt"))
            .executes((sender, args) -> {
                CompoundTag nbt = (CompoundTag) args.get("nbt");
                // Do something with "nbt" here...
            })
            .register();
        // #endregion nbtCompoundArgumentsExampleMojang

        // #region nbtCompoundArgumentsExampleNBTAPI
        new CommandAPICommand("award")
            .withArguments(new NBTCompoundArgument<ReadWriteNBT>("nbt"))
            .executes((sender, args) -> {
                ReadWriteNBT nbt = (ReadWriteNBT) args.get("nbt");
                // Do something with "nbt" here...
            })
            .register();
        // #endregion nbtCompoundArgumentsExampleNBTAPI

        // #region nbtCompoundArgumentsExampleRtagPath
        new CommandAPICommand("test")
            .withArguments(new NBTCompoundArgument<Object>("nbt"))
            .executes((sender, args) -> {
                String result = Rtag.INSTANCE.get(args.get("nbt"), "some", "path", "here");
            })
            .register();
        // #endregion nbtCompoundArgumentsExampleRtagPath
    }
}
