package buildcraft.api.v2.machine;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Namespaced replacement for hard-coded laser table enums. */
public record LaserTableType(Identifier id) {
    public LaserTableType {
        Objects.requireNonNull(id, "id");
    }
}
