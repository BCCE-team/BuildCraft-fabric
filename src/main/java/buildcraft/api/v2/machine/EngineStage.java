package buildcraft.api.v2.machine;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record EngineStage(Identifier id, int severity) {
    public EngineStage {
        Objects.requireNonNull(id, "id");
        if (severity < 0) throw new IllegalArgumentException("severity must be non-negative");
    }
}
