package tips

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.StringArgument
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Predicate

fun predicateTips() {
    val partyMembers = mutableMapOf<UUID, String>()
    // #region exampleStep1
    var arguments = mutableListOf<Argument<*>>()

    // The "create" literal, with a requirement that a player must not have a party
    arguments.add(LiteralArgument("create")
        .withRequirement { !partyMembers.containsKey((it as Player).uniqueId) }
    )

    arguments.add(StringArgument("partyName"))
    // #endregion exampleStep1

    // #region exampleStep2
    arguments = mutableListOf<Argument<*>>()
    arguments.add(LiteralArgument("tp")
        .withRequirement { partyMembers.containsKey((it as Player).uniqueId) })
    // #endregion exampleStep2

    // #region exampleStep3
    val testIfPlayerHasParty = Predicate { sender: CommandSender ->
        partyMembers.containsKey((sender as Player).uniqueId)
    }
    // #endregion exampleStep3

    // #region exampleStep4
    var args = listOf<Argument<*>>(
        LiteralArgument("create").withRequirement(testIfPlayerHasParty.negate()),
        StringArgument("partyName")
    )
    // #endregion exampleStep4

    // #region exampleStep5
    args = listOf<Argument<*>>(LiteralArgument("tp").withRequirement(testIfPlayerHasParty))
    // #endregion exampleStep5
}