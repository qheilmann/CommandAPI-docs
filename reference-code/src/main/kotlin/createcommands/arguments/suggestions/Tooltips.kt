package createcommands.arguments.suggestions

import com.mojang.brigadier.Message
import dev.jorel.commandapi.BukkitTooltip
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.IStringTooltip
import dev.jorel.commandapi.StringTooltip
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.SafeSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun tooltips() {
    // #region createArgumentsWithTooltips
    val arguments = mutableListOf<Argument<*>>()
    arguments.add(
        StringArgument("emote")
            .replaceSuggestions(ArgumentSuggestions.stringsWithTooltips { info ->
                arrayOf<IStringTooltip>(
                    StringTooltip.ofString("wave", "Waves at a player"),
                    StringTooltip.ofString("hug", "Gives a player a hug"),
                    StringTooltip.ofString("glare", "Gives a player the death glare")
                )
            })
    )
    arguments.add(PlayerArgument("target"))
    // #endregion createArgumentsWithTooltips

    // #region registerEmoteCommand
    CommandAPICommand("emote")
        .withArguments(*arguments.toTypedArray())
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val emote = args["emote"] as String
            val target = args["target"] as Player

            when (emote) {
                "wave" -> target.sendMessage("${player.name} waves at you!")
                "hug" -> target.sendMessage("${player.name} hugs you!")
                "glare" -> target.sendMessage("${player.name} gives you the death glare...")
            }
        })
        .register()
    // #endregion registerEmoteCommand

    // #region createCustomItemClass
    class CustomItem(val item: ItemStack, val name: String, lore: String) : IStringTooltip {
        init {
            val meta = item.itemMeta
            meta.setDisplayName(name)
            meta.lore = listOf(lore)
            item.itemMeta = meta
        }

        override fun getSuggestion(): String = this.item.itemMeta.displayName

        override fun getTooltip(): Message = BukkitTooltip.messageFromString(this.item.itemMeta.lore?.get(0) ?: "")
    }
    // #endregion createCustomItemClass

    // #region registerCustomItemCommand
    val customItems = arrayOf<CustomItem>(
        CustomItem(ItemStack(Material.DIAMOND_SWORD), "God sword", "A sword from the heavens"),
        CustomItem(ItemStack(Material.PUMPKIN_PIE), "Sweet pie", "Just like grandma used to make")
    )

    CommandAPICommand("giveitem")
        .withArguments(StringArgument("item").replaceSuggestions(ArgumentSuggestions.stringsWithTooltips(*customItems))) // We use customItems[] as the input for our suggestions with tooltips
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val itemName = args["item"] as String

            // Give them the item
            for (item in customItems) {
                if (item.name == itemName) {
                    player.inventory.addItem(item.item)
                    break
                }
            }
        })
        .register()
    // #endregion registerCustomItemCommand
}

fun tooltips2() {
    // #region createArgumentsWithTooltipsAndSafeSuggestions
    val arguments = listOf<Argument<*>>(
        LocationArgument("location")
            .replaceSafeSuggestions(SafeSuggestions.tooltips { info ->
                // We know the sender is a player if we use .executesPlayer()
                val player = info.sender() as Player
                BukkitTooltip.arrayOf(
                    BukkitTooltip.ofString(player.world.spawnLocation, "World spawn"),
                    BukkitTooltip.ofString(player.bedSpawnLocation, "Your bed"),
                    BukkitTooltip.ofString(player.getTargetBlockExact(256)?.location, "Target block")
                )
            })
    )
    // #endregion createArgumentsWithTooltipsAndSafeSuggestions

    // #region registerWarpCommand
    CommandAPICommand("warp")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            player.teleport(args["location"] as Location)
        })
        .register()
    // #endregion registerWarpCommand
}