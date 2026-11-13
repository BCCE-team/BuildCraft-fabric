package buildcraft.api.v2.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Immutable, client-only render/menu input. Models and textures are asset identifiers, not baked models. */
public record PresentationFrame(
    Identifier model,
    List<Identifier> textureLayers,
    Map<Identifier, Double> animations,
    Identifier sound,
    MenuPresentation menu
) {
    public PresentationFrame {
        textureLayers = List.copyOf(Objects.requireNonNull(textureLayers, "textureLayers"));
        animations = Collections.unmodifiableMap(new LinkedHashMap<>(Objects.requireNonNull(animations, "animations")));
        if (textureLayers.size() > 32 || animations.size() > 64) {
            throw new IllegalArgumentException("Too many presentation layers or channels");
        }
        for (Identifier texture : textureLayers) Objects.requireNonNull(texture, "texture");
        for (Map.Entry<Identifier, Double> entry : animations.entrySet()) {
            Objects.requireNonNull(entry.getKey(), "animation channel");
            Double value = Objects.requireNonNull(entry.getValue(), "animation value");
            if (!Double.isFinite(value) || value < 0 || value > 1) {
                throw new IllegalArgumentException("Animation values must be normalized to [0, 1]");
            }
        }
    }

    public static PresentationFrame empty() {
        return new PresentationFrame(null, List.of(), Map.of(), null, null);
    }

    /** Missing fields inherit the base; a resolver can selectively override individual animation channels. */
    public PresentationFrame over(PresentationFrame base) {
        Objects.requireNonNull(base, "base");
        Map<Identifier, Double> values = new LinkedHashMap<>(base.animations());
        values.putAll(animations);
        return new PresentationFrame(model == null ? base.model : model,
            textureLayers.isEmpty() ? base.textureLayers : textureLayers,
            values, sound == null ? base.sound : sound,
            menu == null ? base.menu : menu);
    }
}
