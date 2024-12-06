package annotations.helpClassExample;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Help;

// #region helpClassExample
@Command("teleport")
@Help("Teleports yourself to another location")
public class TeleportCommand {
    // #endregion helpClassExample
}