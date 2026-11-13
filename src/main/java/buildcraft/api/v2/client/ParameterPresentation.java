package buildcraft.api.v2.client;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Data-only description of a statement parameter editor. */
public record ParameterPresentation(
    Identifier parameterTypeId,
    String titleKey,
    String descriptionKey,
    Identifier icon,
    Identifier editorTypeId
) {
    public ParameterPresentation {
        Objects.requireNonNull(parameterTypeId, "parameterTypeId");
        titleKey = Objects.requireNonNull(titleKey, "titleKey");
        descriptionKey = descriptionKey == null ? "" : descriptionKey;
        Objects.requireNonNull(editorTypeId, "editorTypeId");
    }

    public Optional<Identifier> iconId() { return Optional.ofNullable(icon); }
}
