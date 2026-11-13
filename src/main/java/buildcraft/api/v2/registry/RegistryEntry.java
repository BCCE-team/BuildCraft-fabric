package buildcraft.api.v2.registry;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record RegistryEntry<T>(Identifier id, T value, String owner) {
    public RegistryEntry {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(owner, "owner");
        if (owner.isBlank()) throw new IllegalArgumentException("owner must not be blank");
    }
}
