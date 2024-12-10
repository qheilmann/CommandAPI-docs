package kotlindsl

import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun usage() {
    // #region dslExample
    commandAPICommand("sendmessageto") {
        playerArgument("player") // Defines a new PlayerArgument("player")
        greedyStringArgument("msg") // Defines a new GreedyStringArgument("msg")
        anyExecutor { _, args -> // Command can be executed by anyone and anything (such as entities, the console, etc.)
            val player: Player = args["player"] as Player
            val message: String = args["msg"] as String
            player.sendMessage(message)
        }
    }
    // #endregion dslExample

    // #region argumentRequirementsExample
    commandAPICommand("sendMessageTo") {
        playerArgument("player")
        greedyStringArgument("msg")
        playerExecutor { _, args ->
            val player: Player = args["player"] as Player
            val message: String = args["msg"] as String
            player.sendMessage(message)
        }
    }

    commandAPICommand("sendMessageTo") {
        literalArgument("broadcast") {
            withRequirement { sender: CommandSender -> sender.isOp } // Applies the requirement to the broadcast literal argument
            /* add more methods here that modify argument behavior */
        }
        greedyStringArgument("msg")
        playerExecutor { _, args ->
            val message: String = args["msg"] as String
            Bukkit.broadcastMessage(message)
        }
    }
    // #endregion argumentRequirementsExample

    // #region commandRequirementsExample
    commandAPICommand("commandRequirement") {
        withRequirement { sender: CommandSender -> sender.isOp}
        playerExecutor { player, _ ->
            player.sendMessage("This command can only be executed by players who are server operators.")
        }
    }
    // #endregion commandRequirementsExample

    // #region optionalArgumentsExample
    commandAPICommand("optionalArgument") {
        literalArgument("give")
        itemStackArgument("item")
        integerArgument("amount", optional = true) // This sets the argument as optional
        playerExecutor { player, args ->
            // This command will let you execute:
            // "/optionalArgument give minecraft:stick"
            // "/optionalArgument give minecraft:stick 5"
            val itemStack: ItemStack = args["item"] as ItemStack
            val amount: Int = args.getOptional("amount").orElse(1) as Int
            itemStack.amount = amount
            player.inventory.addItem(itemStack)
        }
    }
    // #endregion optionalArgumentsExample

    // #region replaceSuggestionsExample
    commandAPICommand("replaceSuggestions") {
        stringArgument("strings") {
            replaceSuggestions(ArgumentSuggestions.strings("one", "two", "three")) // Replaces the suggestions for the "strings" StringArgument
        }
        playerExecutor { player, args ->
            player.sendMessage("You chose option ${args["strings"] as String}!")
        }
    }
    // #endregion replaceSuggestionsExample
}

fun usageTree() {
    // #region dslTreeExample
    commandTree("sendmessageto") {
        playerArgument("player") { // Defines a new PlayerArgument("player")
            greedyStringArgument("msg") { // Defines a new GreedyStringArgument("msg")
                anyExecutor { _, args -> // Command can be executed by anyone and anything (such as entities, the console, etc.)
                    val player: Player = args["player"] as Player
                    val message: String = args["msg"] as String
                    player.sendMessage(message)
                }
            }
        }
    }
    // #endregion dslTreeExample

    // #region argumentRequirementsTreeExample
    commandTree("sendMessageTo") {
        playerArgument("player") {
            greedyStringArgument("msg") {
                playerExecutor { _, args ->
                    val player: Player = args["player"] as Player
                    val message: String = args["msg"] as String
                    player.sendMessage(message)
                }
            }
        }
        literalArgument("broadcast") {
            withRequirement { sender: CommandSender -> sender.isOp } // Applies the requirement to the broadcast literal argument
            /* add more methods here that modify argument behavior */
            greedyStringArgument("msg") {
                playerExecutor { _, args ->
                    val message: String = args["msg"] as String
                    Bukkit.broadcastMessage(message)
                }
            }
        }
    }
    // #endregion argumentRequirementsTreeExample

    // #region commandRequirementsTreeExample
    commandTree("commandRequirement") {
        withRequirement { sender: CommandSender -> sender.isOp}
        playerExecutor { player, _ ->
            player.sendMessage("This command can only be executed by players who are server operators.")
        }
    }
    // #endregion commandRequirementsTreeExample

    // #region optionalArgumentsTreeExample
    commandTree("optionalArgument") {
        literalArgument("give") {
            itemStackArgument("item") {
                integerArgument("amount", optional = true) {
                    playerExecutor { player, args ->
                        // This command will let you execute:
                        // "/optionalArgument give minecraft:stick"
                        // "/optionalArgument give minecraft:stick 5"
                        val itemStack: ItemStack = args["item"] as ItemStack
                        val amount: Int = args.getOptional("amount").orElse(1) as Int
                        itemStack.amount = amount
                        player.inventory.addItem(itemStack)
                    }
                }
            }
        }
    }
    // #endregion optionalArgumentsTreeExample

    // #region replaceSuggestionsTreeExample
    commandTree("replaceSuggestions") {
        stringArgument("strings") {
            replaceSuggestions(ArgumentSuggestions.strings("one", "two", "three")) // Replaces the suggestions for the "strings" StringArgument
            playerExecutor { player, args ->
                player.sendMessage("You chose option ${args["strings"] as String}!")
            }
        }
    }
    // #endregion replaceSuggestionsTreeExample
}