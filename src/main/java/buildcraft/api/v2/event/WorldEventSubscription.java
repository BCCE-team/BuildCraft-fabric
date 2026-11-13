package buildcraft.api.v2.event;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Immutable registration metadata; not a mutable event object. */
public record WorldEventSubscription(Identifier id, int priority, WorldEventListener listener) {
    public WorldEventSubscription {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(listener, "listener");
    }
}
