package buildcraft.api.v2.fluid;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Representation-neutral serialized form used by the API codec. */
public final class FluidVariantData {
    private final Identifier fluidId;
    private final FluidComponentPayload components;

    public FluidVariantData(Identifier fluidId, FluidComponentPayload components) {
        this.fluidId = Objects.requireNonNull(fluidId, "fluidId");
        this.components = Objects.requireNonNull(components, "components");
    }

    public Identifier fluidId() {
        return fluidId;
    }

    public FluidComponentPayload components() {
        return components;
    }
}
