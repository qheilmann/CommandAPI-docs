package createcommands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.SafeSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

class Requirements {
    static {
        // #region baseOnPlayerLevelExample
        new CommandAPICommand("repair")
            .withRequirement(sender -> ((Player) sender).getLevel() >= 30)
            .executesPlayer((player, args) -> {

                // Repair the item back to full durability
                ItemStack is = player.getInventory().getItemInMainHand();
                ItemMeta itemMeta = is.getItemMeta();
                if (itemMeta instanceof Damageable damageable) {
                    damageable.setDamage(0);
                    is.setItemMeta(itemMeta);
                }

                // Subtract 30 levels
                player.setLevel(player.getLevel() - 30);
            })
            .register();
        // #endregion baseOnPlayerLevelExample

        // #region partySystemExampleStep1
        Map<UUID, String> partyMembers = new HashMap<>();
        // #endregion partySystemExampleStep1

        // #region partySystemExampleStep2
        List<Argument<?>> arguments = new ArrayList<>();

        // The "create" literal, with a requirement that a player must not have a party
        arguments.add(new LiteralArgument("create")
            .withRequirement(sender -> !partyMembers.containsKey(((Player) sender).getUniqueId()))
        );

        arguments.add(new StringArgument("partyName"));
        // #endregion partySystemExampleStep2

        // #region partySystemExampleStep3
        new CommandAPICommand("party")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {

                // Get the name of the party to create
                String partyName = (String) args.get("partyName");

                partyMembers.put(player.getUniqueId(), partyName);
            })
            .register();
        // #endregion partySystemExampleStep3

        // #region partySystemExampleStep4
        arguments = new ArrayList<>();
        arguments.add(new LiteralArgument("tp")
            .withRequirement(sender -> partyMembers.containsKey(((Player) sender).getUniqueId()))
        );

        arguments.add(new PlayerArgument("player")
            .replaceSafeSuggestions(SafeSuggestions.suggest(info -> {

                // Store the list of party members to teleport to
                List<Player> playersToTeleportTo = new ArrayList<>();

                String partyName = partyMembers.get(((Player) info.sender()).getUniqueId());
                // Find the party members
                for (Entry<UUID, String> entry : partyMembers.entrySet()) {

                    // Ignore yourself
                    if (entry.getKey().equals(((Player) info.sender()).getUniqueId())) {
                        continue;
                    } else {
                        // If the party member is in the same party as you
                        if (entry.getValue().equals(partyName)) {
                            Player target = Bukkit.getPlayer(entry.getKey());
                            if (target.isOnline()) {
                                // Add them if they are online
                                playersToTeleportTo.add(target);
                            }
                        }
                    }
                }

                return playersToTeleportTo.toArray(new Player[0]);
            })));
        // #endregion partySystemExampleStep4

        // #region partySystemExampleStep5
        new CommandAPICommand("party")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {
                Player target = (Player) args.get("player");
                player.teleport(target);
            })
            .register();
        // #endregion partySystemExampleStep5

        // #region updateRequirementsExample
        new CommandAPICommand("party")
            .withArguments(arguments)
            .executesPlayer((player, args) -> {

                // Get the name of the party to create
                String partyName = (String) args.get("partyName");

                partyMembers.put(player.getUniqueId(), partyName);

                CommandAPI.updateRequirements(player);
            })
            .register();
        // #endregion updateRequirementsExample

        // #region multipleRequirementsExample
        new CommandAPICommand("someCommand")
            .withRequirement(sender -> ((Player) sender).getLevel() >= 30)
            .withRequirement(sender -> ((Player) sender).getInventory().contains(Material.DIAMOND_PICKAXE))
            .withRequirement(sender -> ((Player) sender).isInvulnerable())
            .executesPlayer((player, args) -> {
                // Code goes here
            })
            .register();
        // #endregion multipleRequirementsExample
    }
}
