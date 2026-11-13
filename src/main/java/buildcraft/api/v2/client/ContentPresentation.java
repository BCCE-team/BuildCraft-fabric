package buildcraft.api.v2.client;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Client-facing metadata kept free of net.minecraft.client classes. */
public record ContentPresentation(Identifier contentId, String titleKey, String descriptionKey, Identifier icon, Identifier model) {
    public ContentPresentation {
        Objects.requireNonNull(contentId, "contentId");
        titleKey = Objects.requireNonNull(titleKey, "titleKey");
        descriptionKey = descriptionKey == null ? "" : descriptionKey;
    }
    public Optional<Identifier> iconId() { return Optional.ofNullable(icon); }
    public Optional<Identifier> modelId() { return Optional.ofNullable(model); }
}
