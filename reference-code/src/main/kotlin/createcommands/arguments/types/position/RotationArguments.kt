package createcommands.arguments.types.position

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.RotationArgument
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOneEntity
import dev.jorel.commandapi.kotlindsl.rotationArgument
import dev.jorel.commandapi.wrappers.Rotation
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.util.EulerAngle

fun rotationArguments() {
    // #region rotationArgumentsExample
    CommandAPICommand("rotate")
        .withArguments(RotationArgument("rotation"))
        .withArguments(EntitySelectorArgument.OneEntity("target"))
        .executes(CommandExecutor { _, args ->
            val rotation = args["rotation"] as Rotation
            val target = args["target"] as Entity

            if (target is ArmorStand) {
                target.headPose = EulerAngle(Math.toRadians(rotation.pitch.toDouble()), Math.toRadians(rotation.yaw.toDouble() - 90), 0.0)
            }
        })
        .register()
    // #endregion rotationArgumentsExample
}

fun rotationArgumentsDSL() {
    // #region rotationArgumentsExampleDSL
    commandAPICommand("rotate") {
        rotationArgument("rotation")
        entitySelectorArgumentOneEntity("target")
        anyExecutor { _, args ->
            val rotation = args["rotation"] as Rotation
            val target = args["target"] as Entity

            if (target is ArmorStand) {
                target.headPose = EulerAngle(Math.toRadians(rotation.pitch.toDouble()), Math.toRadians(rotation.yaw.toDouble() - 90), 0.0)
            }
        }
    }
    // #endregion rotationArgumentsExampleDSL
}