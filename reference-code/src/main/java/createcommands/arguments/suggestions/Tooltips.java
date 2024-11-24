package createcommands.arguments.suggestions;

import com.mojang.brigadier.Message;
import dev.jorel.commandapi.BukkitTooltip;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.StringTooltip;
import dev.jorel.commandapi.arguments.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Tooltips {
    {
        // #region stringSuggestionTooltipsExampleDeclare
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new StringArgument("emote")
                .replaceSuggestions(ArgumentSuggestions.stringsWithTooltips(info ->
                        new IStringTooltip[]{
                                StringTooltip.ofString("wave", "Waves at a player"),
                                StringTooltip.ofString("hug", "Gives a player a hug"),
                                StringTooltip.ofString("glare", "Gives a player the death glare")
                        }
                ))
        );
        arguments.add(new PlayerArgument("target"));
        // #endregion stringSuggestionTooltipsExampleDeclare

        // #region stringSuggestionTooltipsExampleRegister
        new CommandAPICommand("emote")
                .withArguments(arguments)
                .executesPlayer((player, args) -> {
                    String emote = (String) args.get("emote");
                    Player target = (Player) args.get("target");

                    switch (emote) {
                        case "wave":
                            target.sendMessage(player.getName() + " waves at you!");
                            break;
                        case "hug":
                            target.sendMessage(player.getName() + " hugs you!");
                            break;
                        case "glare":
                            target.sendMessage(player.getName() + " gives you the death glare...");
                            break;
                        default:
                            player.sendMessage("Invalid emote '" + emote + "'!");
                            break;
                    }
                })
                .register();
        // #endregion stringSuggestionTooltipsExampleRegister
    }

    // #region customTooltipExampleDeclare
    @SuppressWarnings("deprecation")
    class CustomItem implements IStringTooltip {

        private ItemStack itemstack;
        private String name;

        public CustomItem(ItemStack itemstack, String name, String lore) {
            ItemMeta meta = itemstack.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(Collections.singletonList(lore));
            itemstack.setItemMeta(meta);
            this.itemstack = itemstack;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public ItemStack getItem() {
            return this.itemstack;
        }

        @Override
        public String getSuggestion() {
            return this.itemstack.getItemMeta().getDisplayName();
        }

        @Override
        public Message getTooltip() {
            return BukkitTooltip.messageFromString(this.itemstack.getItemMeta().getLore().get(0));
        }

    }
    // #endregion customTooltipExampleDeclare

    {
        // #region customTooltipExampleRegister
        CustomItem[] customItems = new CustomItem[]{
                new CustomItem(new ItemStack(Material.DIAMOND_SWORD), "God sword", "A sword from the heavens"),
                new CustomItem(new ItemStack(Material.PUMPKIN_PIE), "Sweet pie", "Just like grandma used to make")
        };

        new CommandAPICommand("giveitem")
                .withArguments(new StringArgument("item").replaceSuggestions(ArgumentSuggestions.stringsWithTooltips(customItems))) // We use customItems[] as the input for our suggestions with tooltips
                .executesPlayer((player, args) -> {
                    String itemName = (String) args.get("item");

                    // Give them the item
                    for (CustomItem item : customItems) {
                        if (item.getName().equals(itemName)) {
                            player.getInventory().addItem(item.getItem());
                            break;
                        }
                    }
                })
                .register();
        // #endregion customTooltipExampleRegister
    }

    {
        // #region tooltipsWithSafeSuggestionsExampleDeclare
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new LocationArgument("location")
                .replaceSafeSuggestions(SafeSuggestions.tooltips(info -> {
                    // We know the sender is a player if we use .executesPlayer()
                    Player player = (Player) info.sender();
                    return BukkitTooltip.arrayOf(
                            BukkitTooltip.ofString(player.getWorld().getSpawnLocation(), "World spawn"),
                            BukkitTooltip.ofString(player.getBedSpawnLocation(), "Your bed"),
                            BukkitTooltip.ofString(player.getTargetBlockExact(256).getLocation(), "Target block")
                    );
                })));
        // #endregion tooltipsWithSafeSuggestionsExampleDeclare

        // #region tooltipsWithSafeSuggestionsExampleRegister
        new CommandAPICommand("warp")
                .withArguments(arguments)
                .executesPlayer((player, args) -> {
                    player.teleport((Location) args.get("location"));
                })
                .register();
        // #endregion tooltipsWithSafeSuggestionsExampleRegister
    }
}