package createcommands;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;

import java.util.Locale;

class Help {
    {
        // #region helpExampleStep1
        new CommandAPICommand("mycmd")
            .withShortDescription("Says hi")
            .withFullDescription("Broadcasts hi to everyone on the server")
            .executes((sender, args) -> {
                Bukkit.broadcastMessage("Hi!");
            })
            .register();
        // #endregion helpExampleStep1

        // #region helpExampleStep2
        new CommandAPICommand("mycmd")
            .withHelp("Says hi", "Broadcasts hi to everyone on the server")
            .executes((sender, args) -> {
                Bukkit.broadcastMessage("Hi!");
            })
            .register();
        // #endregion helpExampleStep2

        // #region helpTopicExampleStep2
        new CommandAPICommand("mycmd")
            .withHelp(makeHelp("mycmd"))
            .executes((sender, args) -> {
                Bukkit.broadcastMessage("Hi!");
            })
            .register();
        // #endregion helpTopicExampleStep2
    }

    // #region helpTopicExampleStep1
    public HelpTopic makeHelp(String command) {
        return new HelpTopic() {
            @Override
            public String getShortText() {
                return "Says hi";
            }

            @Override
            public String getFullText(CommandSender forWho) {
                String helpText = "";
                if (forWho instanceof Player player) {
                    // Make use of the player's locale to make language-specific help!
                    Locale playerLocale = player.locale();
                    if (playerLocale.getLanguage().equals("en")) {
                        helpText = "Broadcasts \"Hi!\" to everyone on the server";
                    } else if (playerLocale.getLanguage().equals("de")) {
                        helpText = "Sendet \"Hi!\" an alle auf dem Server";
                    }
                } else {
                    helpText = "Broadcasts \"Hi!\" to everyone on the server";
                }
                return helpText;
            }

            // Allow anyone to see this help topic
            @Override
            public boolean canSee(CommandSender player) {
                return true;
            }
        };
    }
    // #endregion helpTopicExampleStep1
}
