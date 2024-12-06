package annotations.permissionClassExample;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Permission;

// #region permissionClassExample
@Command("teleport")
@Permission("myplugin.tp")
public class TeleportCommand {
    // #endregion permissionClassExample
}