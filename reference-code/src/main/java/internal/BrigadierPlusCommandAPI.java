package internal;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.jorel.commandapi.Brigadier;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;

import java.util.ArrayList;
import java.util.List;

class BrigadierPlusCommandAPI {
    {
        // #region addPredicateExample
        // #region addPredicateExampleStep1
        // Register literal "randomchance"
        LiteralCommandNode randomChance = Brigadier.fromLiteralArgument(new LiteralArgument("randomchance")).build();
        // #endregion addPredicateExampleStep1

        // #region addPredicateExampleStep2
        // Declare arguments like normal
        Argument<Integer> numeratorArgument = new IntegerArgument("numerator", 0);
        Argument<Integer> denominatorArgument = new IntegerArgument("denominator", 1);

        List<Argument> arguments = new ArrayList<>();
        arguments.add(numeratorArgument);
        arguments.add(denominatorArgument);
        // #endregion addPredicateExampleStep2

        // #region addPredicateExampleStep3
        ArgumentBuilder numerator = Brigadier.fromArgument(numeratorArgument);
        // #region addPredicateExampleStep4
        ArgumentBuilder denominator = Brigadier.fromArgument(denominatorArgument)
            // #endregion addPredicateExampleStep3
            // Fork redirecting to "execute" and state our predicate
            .fork(Brigadier.getRootNode().getChild("execute"), Brigadier.fromPredicate((sender, args) -> {
                // Parse arguments like normal
                int num = (int) args[0];
                int denom = (int) args[1];

                // Return boolean with a num/denom chance
                return Math.ceil(Math.random() * denom) <= num;
            }, arguments));
        // #endregion addPredicateExampleStep4

        // #region addPredicateExampleStep5
        // Add <numerator> <denominator> as a child of randomchance
        randomChance.addChild(numerator.then(denominator).build());
        // #endregion addPredicateExampleStep5

        // #region addPredicateExampleStep6
        // Add (randomchance <numerator> <denominator>) as a child of (execute -> if)
        Brigadier.getRootNode().getChild("execute").getChild("if").addChild(randomChance);
        // #endregion addPredicateExampleStep6
        // #endregion addPredicateExample
    }
}
