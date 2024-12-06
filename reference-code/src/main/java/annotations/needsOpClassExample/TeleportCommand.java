package annotations.needsOpClassExample;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.NeedsOp;

// #region needsOpClassExample
@Command("teleport")
@NeedsOp
public class TeleportCommand {
    // #endregion needsOpClassExample
}
