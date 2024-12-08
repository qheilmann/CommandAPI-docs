package utils;

import dev.jorel.commandapi.Converter;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

class Conversion {
    // #region simpleConvertExample
    public class YourPlugin extends JavaPlugin {
        @Override
        public void onEnable() {
            Converter.convert((JavaPlugin) Bukkit.getPluginManager().getPlugin("TargetPlugin"));
            // Other code goes here...
        }
    }
    // #endregion simpleConvertExample

    static {
        // #region convertSpeedCommandExample
        JavaPlugin essentials = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Essentials");

        // /speed <speed>
        Converter.convert(essentials, "speed", new IntegerArgument("speed", 0, 10));

        // /speed <target>
        Converter.convert(essentials, "speed", new PlayerArgument("target"));

        // /speed <walk/fly> <speed>
        Converter.convert(essentials, "speed",
            new MultiLiteralArgument("modes", "walk", "fly"),
            new IntegerArgument("speed", 0, 10)
        );

        // /speed <walk/fly> <speed> <target>
        Converter.convert(essentials, "speed",
            new MultiLiteralArgument("modes", "walk", "fly"),
            new IntegerArgument("speed", 0, 10),
            new PlayerArgument("target")
        );
        // #endregion convertSpeedCommandExample
    }
}
