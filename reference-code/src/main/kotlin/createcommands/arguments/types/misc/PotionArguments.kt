package createcommands.arguments.types.misc

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.PotionEffectArgument
import dev.jorel.commandapi.arguments.TimeArgument
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

fun potionArguments() {
    // #region potionEffectArgumentsExample
    CommandAPICommand("potion")
        .withArguments(PlayerArgument("target"))
        .withArguments(PotionEffectArgument("potion"))
        .withArguments(TimeArgument("duration"))
        .withArguments(IntegerArgument("strength"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val target = args["target"] as Player
            val potion = args["potion"] as PotionEffectType
            val duration = args["duration"] as Int
            val strength = args["strength"] as Int

            // Add the potion effect to the target player
            target.addPotionEffect(PotionEffect(potion, duration, strength))
        })
        .register()
    // #endregion potionEffectArgumentsExample

    // #region potionEffectArgumentsNamespacedKeyExample
    CommandAPICommand("potion")
        .withArguments(PlayerArgument("target"))
        .withArguments(PotionEffectArgument.NamespacedKey("potion"))
        .withArguments(TimeArgument("duration"))
        .withArguments(IntegerArgument("strength"))
        .executes(NormalExecutor<CommandSender, Any> { _, args ->
            val target = args["target"] as Player
            val potionKey = args["potion"] as NamespacedKey
            val duration = args["duration"] as Int
            val strength = args["strength"] as Int

            val potion = PotionEffectType.getByKey(potionKey)!!

            // Add the potion effect to the target player
            target.addPotionEffect(PotionEffect(potion, duration, strength))
        })
        .register()
    // #endregion potionEffectArgumentsNamespacedKeyExample
}

fun potionArgumentsDSL() {
    // region potionEffectArgumentsExampleDSL
    commandAPICommand("potion") {
        playerArgument("target")
        potionEffectArgument("potion")
        timeArgument("duration")
        integerArgument("strength")
        anyExecutor { _, args ->
            val target = args["target"] as Player
            val potion = args["potion"] as PotionEffectType
            val duration = args["duration"] as Int
            val strength = args["strength"] as Int

            // Add the potion effect to the target player
            target.addPotionEffect(PotionEffect(potion, duration, strength))
        }
    }
    // endregion potionEffectArgumentsExampleDSL

    // region potionEffectArgumentsNamespacedKeyExampleDSL
    commandAPICommand("potion") {
        playerArgument("target")
        potionEffectArgument("potion", true)
        timeArgument("duration")
        integerArgument("strength")
        anyExecutor { _, args ->
            val target = args["target"] as Player
            val potionKey = args["potion"] as NamespacedKey
            val duration = args["duration"] as Int
            val strength = args["strength"] as Int

            val potion = PotionEffectType.getByKey(potionKey)!!

            // Add the potion effect to the target player
            target.addPotionEffect(PotionEffect(potion, duration, strength))
        }
    }
    // endregion potionEffectArgumentsNamespacedKeyExampleDSL
}