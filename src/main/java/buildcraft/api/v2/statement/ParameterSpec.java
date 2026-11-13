package buildcraft.api.v2.statement;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record ParameterSpec(Identifier slotId, Identifier typeId, boolean required) {
    public ParameterSpec {
        Objects.requireNonNull(slotId, "slotId");
        Objects.requireNonNull(typeId, "typeId");
    }
}
