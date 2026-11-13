package buildcraft.api.v2.fuels;

import buildcraft.api.v2.reload.DefinitionProvenance;
import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Resolved matching profile together with its stable id and provenance. */
public record ProfileMatch<T>(Identifier id, T profile, DefinitionProvenance provenance) {
    public ProfileMatch {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(profile, "profile");
        Objects.requireNonNull(provenance, "provenance");
    }
}
