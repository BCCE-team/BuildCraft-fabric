package buildcraft.api.v2.statement;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public interface StatementService {
    Optional<ParameterType<?>> parameterType(Identifier id);
    Optional<TriggerType> triggerType(Identifier id);
    Optional<ActionType> actionType(Identifier id);
    Collection<Identifier> availableTriggers(StatementContext context);
    Collection<Identifier> availableActions(StatementContext context);
    /** Restricts discovery to a single placement without altering legacy discovery. */
    default Collection<Identifier> availableTriggers(StatementContext context, StatementScope scope) {
        return scope == StatementScope.INTERNAL ? availableTriggers(context) : java.util.List.of();
    }
    default Collection<Identifier> availableActions(StatementContext context, StatementScope scope) {
        return scope == StatementScope.INTERNAL ? availableActions(context) : java.util.List.of();
    }
    boolean evaluateTrigger(Identifier id, StatementContext context, StatementParameters parameters);
    StatementResult executeAction(Identifier id, StatementContext context, StatementParameters parameters);
}
