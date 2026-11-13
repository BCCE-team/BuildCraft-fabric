package buildcraft.api.v2;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** One independently versioned API capability. */
public record ApiFeature(Identifier id, int level) {
    public ApiFeature {
        Objects.requireNonNull(id, "id");
        if (level < 1) throw new IllegalArgumentException("feature level must be >= 1");
    }
}
