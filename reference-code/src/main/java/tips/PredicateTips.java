package tips;

import com.velocitypowered.api.proxy.Player;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

class PredicateTips {
    static {
        Map<UUID, String> partyMembers = null;
        // #region exampleStep1
        List<Argument<?>> arguments = new ArrayList<>();

        // The "create" literal, with a requirement that a player must not have a party
        arguments.add(new LiteralArgument("create")
            .withRequirement(sender -> !partyMembers.containsKey(((Player) sender).getUniqueId()))
        );

        arguments.add(new StringArgument("partyName"));
        // #endregion exampleStep1

        // #region exampleStep2
        arguments = new ArrayList<>();
        arguments.add(new LiteralArgument("tp")
            .withRequirement(sender -> partyMembers.containsKey(((Player) sender).getUniqueId()))
        );
        // #endregion exampleStep2

        // #region exampleStep3
        Predicate<CommandSender> testIfPlayerHasParty = sender -> {
            return partyMembers.containsKey(((Player) sender).getUniqueId());
        };
        // #endregion exampleStep3

        // #region exampleStep4
        List<Argument<?>> args = new ArrayList<>();
        args.add(new LiteralArgument("create").withRequirement(testIfPlayerHasParty.negate()));
        args.add(new StringArgument("partyName"));
        // #endregion exampleStep4

        // #region exampleStep5
        args = new ArrayList<>();
        args.add(new LiteralArgument("tp").withRequirement(testIfPlayerHasParty));
        // #endregion exampleStep5
    }
}
