package buildcraft.api.v2.fuels;

import buildcraft.api.v2.fluid.FluidVariantData;
import net.minecraft.resources.Identifier;

/** Structured serializable form of a built-in fluid selector. */
public record FluidSelectorData(Kind kind, Identifier id, FluidVariantData variant) {
    public enum Kind {
        FLUID,
        TAG,
        EXACT_VARIANT
    }
}
