package internal

import com.mojang.brigadier.tree.LiteralCommandNode
import dev.jorel.commandapi.Brigadier
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.LiteralArgument
import org.bukkit.command.CommandSender
import kotlin.math.ceil

fun brigadierPlusCommandAPI() {
    // #region addPredicateExample
    // #region addPredicateExampleStep1
    // Register literal "randomchance"
    val randomChance: LiteralCommandNode<Any> = Brigadier.fromLiteralArgument(LiteralArgument("randomchance")).build()
    // #endregion addPredicateExampleStep1

    // #region addPredicateExampleStep2
    // Declare arguments like normal
    val numeratorArgument = IntegerArgument("numerator", 0)
    val denominatorArgument = IntegerArgument("denominator", 1)

    val arguments = listOf<Argument<*>>(numeratorArgument, denominatorArgument)
    // #endregion addPredicateExampleStep2

    // #region addPredicateExampleStep3
    val numerator = Brigadier.fromArgument(numeratorArgument)
    // #region addPredicateExampleStep4
    val denominator = Brigadier.fromArgument(denominatorArgument)
        // #endregion addPredicateExampleStep3
        // Fork redirecting to "execute" and state our predicate
        .fork(Brigadier.getRootNode().getChild("execute"), Brigadier.fromPredicate( { _: CommandSender, args ->
            // Parse arguments like normal
            val num = (args[0] as Int).toDouble()
            val denom = (args[1] as Int).toDouble()

            // Return boolean with a num/denom chance
            ceil(Math.random() * denom) <= num
        }, arguments))
    // #endregion addPredicateExampleStep4

    // #region addPredicateExampleStep5
    // Add <numerator> <denominator> as a child of randomchance
    randomChance.addChild(numerator.then(denominator).build())
    // #endregion addPredicateExampleStep5

    // #region addPredicateExampleStep6
    // Add (randomchance <numerator> <denominator>) as a child of (execute -> if)
    Brigadier.getRootNode().getChild("execute").getChild("if").addChild(randomChance);
    // #endregion addPredicateExampleStep6
    // #endregion addPredicateExample
}
