package createcommands.arguments.types;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.NBTCompoundArgument;
import org.bukkit.plugin.java.JavaPlugin;

class NBTArguments {
    {
        new JavaPlugin() {
            // #region hookNbtAPIExample
            @Override
            public void onLoad() {
                CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                    .initializeNBTAPI(NBTContainer.class, NBTContainer::new)
                );
            }
            // #endregion hookNbtAPIExample
        };

        // #region nbtCompoundArgumentsExample
        new CommandAPICommand("award")
            .withArguments(new NBTCompoundArgument<NBTContainer>("nbt"))
            .executes((sender, args) -> {
                NBTContainer nbt = (NBTContainer) args.get("nbt");
                // Do something with "nbt" here...
            })
            .register();
        // #endregion nbtCompoundArgumentsExample
    }
}
