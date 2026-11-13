package buildcraft.api.v2.statement;

import net.minecraft.resources.Identifier;

public interface StatementCollector {
    void addTrigger(Identifier triggerId);
    void addAction(Identifier actionId);
}
