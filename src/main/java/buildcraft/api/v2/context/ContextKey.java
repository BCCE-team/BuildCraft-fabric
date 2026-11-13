package buildcraft.api.v2.context;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Typed key used to expose scoped API views without leaking implementation objects. */
public record ContextKey<T>(Identifier id, Class<T> type) {
    public ContextKey {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(type, "type");
    }

    public static <T> ContextKey<T> of(Identifier id, Class<T> type) {
        return new ContextKey<>(id, type);
    }
}
