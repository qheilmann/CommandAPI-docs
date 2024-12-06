package annotations.shortHelpClassExample;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Help;

// #region shortHelpClassExample
@Command("teleport")
@Help(value = "Teleports yourself to another location", shortDescription = "TP to a location")
public class TeleportCommand {
    // #endregion shortHelpClassExample
}