package createcommands.arguments.types.position;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.RotationArgument;
import dev.jorel.commandapi.wrappers.Rotation;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.util.EulerAngle;

class RotationArguments {
    static {
        // #region rotationArgumentsExample
        new CommandAPICommand("rotate")
            .withArguments(new RotationArgument("rotation"))
            .withArguments(new EntitySelectorArgument.OneEntity("target"))
            .executes((sender, args) -> {
                Rotation rotation = (Rotation) args.get("rotation");
                Entity target = (Entity) args.get("target");

                if (target instanceof ArmorStand armorStand) {
                    armorStand.setHeadPose(new EulerAngle(Math.toRadians(rotation.getPitch()), Math.toRadians(rotation.getYaw() - 90), 0));
                }
            })
            .register();
        // #endregion rotationArgumentsExample
    }
}
