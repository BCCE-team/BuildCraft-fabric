package buildcraft.api.v2.machine;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record EngineType(Identifier id, EngineProfile profile) {
    public EngineType {
        Objects.requireNonNull(id, "id"); Objects.requireNonNull(profile, "profile");
    }
}
