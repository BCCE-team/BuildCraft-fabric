package buildcraft.api.v2.client;

import java.util.List;
import java.util.Objects;
import net.minecraft.resources.Identifier;

public record PipePresentation(ContentPresentation base, List<Identifier> textureLayers) {
    public PipePresentation {
        Objects.requireNonNull(base, "base"); textureLayers = List.copyOf(Objects.requireNonNull(textureLayers, "textureLayers"));
    }
}
