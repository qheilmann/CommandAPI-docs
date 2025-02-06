package createcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.NormalExecutor
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.help.HelpTopic

fun help() {
    // #region helpExampleStep1
    CommandAPICommand("mycmd")
        .withShortDescription("Says hi")
        .withFullDescription("Broadcasts hi to everyone on the server")
        .executes(NormalExecutor<CommandSender, Any> { _, _ ->
            Bukkit.broadcastMessage("Hi!")
        })
        .register()
    // #endregion helpExampleStep1

    // #region helpExampleStep2
    CommandAPICommand("mycmd")
        .withHelp("Says hi", "Broadcasts hi to everyone on the server")
        .executes(NormalExecutor<CommandSender, Any> { _, _ ->
            Bukkit.broadcastMessage("Hi!")
        })
        .register()
    // #endregion helpExampleStep2

    // #region helpTopicExampleStep1
    fun makeHelp(command: String): HelpTopic = object: HelpTopic() {
        override fun getShortText(): String = "Says hi"

        override fun getFullText(forWho: CommandSender): String {
            var helpText = ""
            if (forWho is Player) {
                // Make use of the player's locale to make language-specific help!
                val playerLocale = forWho.locale()
                if (playerLocale.language == "en") {
                    helpText = "Broadcasts \"Hi!\" to everyone on the server"
                } else if (playerLocale.language == "de") {
                    helpText = "Sendet \"Hi!\" an alle auf dem Server"
                }
            } else {
                helpText = "Broadcasts \"Hi!\" to everyone on the server"
            }
            return helpText
        }

        // Allow anyone to see this help topic
        override fun canSee(player: CommandSender): Boolean = true
    }
    // #endregion helpTopicExampleStep1

    // #region helpTopicExampleStep2
    return CommandAPICommand("mycmd")
        .withHelp(makeHelp("mycmd"))
        .executes(NormalExecutor<CommandSender, Any> { _, _ ->
            Bukkit.broadcastMessage("Hi!")
        })
        .register()
    // #endregion helpTopicExampleStep2
}

fun helpDSL() {
    // #region helpExampleStep1DSL
    commandAPICommand("mycmd") {
        withShortDescription("Says hi")
        withFullDescription("Broadcasts hi to everyone on the server")
        anyExecutor { _, _ ->
            Bukkit.broadcastMessage("Hi!")
        }
    }
    // #endregion helpExampleStep1DSL

    // #region helpExampleStep2DSL
    commandAPICommand("mycmd") {
        withHelp("Says hi", "Broadcasts hi to everyone on the server")
        anyExecutor { _, _ ->
            Bukkit.broadcastMessage("Hi!")
        }
    }
    // #endregion helpExampleStep2DSL
}