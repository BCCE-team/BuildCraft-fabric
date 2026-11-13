package buildcraft.api.v2.service;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Stable, namespaced key for a runtime service exposed by API v2. */
public record ServiceKey<T>(Identifier id) {
    public ServiceKey {
        Objects.requireNonNull(id, "id");
    }

    public static <T> ServiceKey<T> of(Identifier id) {
        return new ServiceKey<>(id);
    }
}
