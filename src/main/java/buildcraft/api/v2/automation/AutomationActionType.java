package buildcraft.api.v2.automation;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record AutomationActionType<R extends AutomationRequest>(Identifier id, Class<R> requestType, AutomationHandler<R> handler) {
    public AutomationActionType {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(requestType, "requestType");
        Objects.requireNonNull(handler, "handler");
    }
}
