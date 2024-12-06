package annotations.aliasClassExample;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;

// #region aliasClassExample
@Command("teleport")
@Alias({"tp", "tele"})
public class TeleportCommand {
    // #endregion aliasClassExample
}
