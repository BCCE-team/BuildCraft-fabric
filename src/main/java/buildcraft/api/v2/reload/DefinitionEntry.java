package buildcraft.api.v2.reload;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record DefinitionEntry<V>(Identifier id, V value, DefinitionProvenance provenance) {
    public DefinitionEntry {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(provenance, "provenance");
    }
}
