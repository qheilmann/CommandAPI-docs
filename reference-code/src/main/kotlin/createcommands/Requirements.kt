package createcommands

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.Damageable
import java.util.*

fun requirements() {
    // #region baseOnPlayerLevelExample
    CommandAPICommand("repair")
        .withRequirement { (it as Player).level >= 30 }
        .executesPlayer(PlayerCommandExecutor { player, _ ->

            // Repair the item back to full durability
            val item = player.inventory.itemInMainHand
            val itemMeta = item.itemMeta
            if (itemMeta is Damageable) {
                itemMeta.damage = 0
                item.setItemMeta(itemMeta)
            }

            // Subtract 30 levels
            player.level -= 30
        })
        .register()
    // #endregion baseOnPlayerLevelExample

    // #region partySystemExampleStep1
    val partyMembers = mutableMapOf<UUID, String>()
    // #endregion partySystemExampleStep1

    // #region partySystemExampleStep2
    var arguments = mutableListOf<Argument<*>>()

    // The "create" literal, with a requirement that a player must have a party
    arguments.add(LiteralArgument("create")
        .withRequirement { !partyMembers.containsKey((it as Player).uniqueId) }
    )

    arguments.add(StringArgument("partyName"))
    // #endregion partySystemExampleStep2

    // #region partySystemExampleStep3
    CommandAPICommand("party")
        .withArguments(*arguments.toTypedArray())
        .executesPlayer(PlayerCommandExecutor { player, args ->

            // Get the name of the party to create
            val partyName = args["partyName"] as String

            partyMembers[player.uniqueId] = partyName
        })
        .register()
    // #endregion partySystemExampleStep3

    // #region partySystemExampleStep4
    arguments = mutableListOf<Argument<*>>()
    arguments.add(LiteralArgument("tp")
        .withRequirement { partyMembers.containsKey((it as Player).uniqueId) })

    arguments.add(PlayerArgument("player")
        .replaceSafeSuggestions(SafeSuggestions.suggest { info ->

            // Store the list of party members to teleport to
            val playersToTeleportTo = mutableListOf<Player>()

            val partyName = partyMembers[(info.sender() as Player).uniqueId]

            // Find the party members
            for ((uuid, party) in partyMembers) {

                // Ignore yourself
                if (uuid == (info.sender() as Player).uniqueId) {
                    continue
                } else {
                    // If the party member is in the same party as you
                    if (party == partyName) {
                        val target = Bukkit.getPlayer(uuid)!!
                        if (target.isOnline) {
                            // Add them if they are online
                            playersToTeleportTo.add(target)
                        }
                    }
                }
            }

            playersToTeleportTo.toTypedArray()
        }))
    // #endregion partySystemExampleStep4

    // #region partySystemExampleStep5
    CommandAPICommand("party")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->
            val target = args["player"] as Player
            player.teleport(target)
        })
        .register()
    // #endregion partySystemExampleStep5

    // #region updateRequirementsExample
    CommandAPICommand("party")
        .withArguments(arguments)
        .executesPlayer(PlayerCommandExecutor { player, args ->

            // Get the name of the party to create
            val partyName = args["partyName"] as String

            partyMembers[player.uniqueId] = partyName

            CommandAPI.updateRequirements(player)
        })
        .register()
    // #endregion updateRequirementsExample

    // #region multipleRequirementsExample
    CommandAPICommand("someCommand")
        .withRequirement { (it as Player).level >= 30 }
        .withRequirement { (it as Player).inventory.contains(Material.DIAMOND_PICKAXE) }
        .withRequirement { (it as Player).isInvulnerable }
        .executesPlayer(PlayerCommandExecutor { player, args ->
            // Code goes here
        })
        .register()
    // #endregion multipleRequirementsExample
}