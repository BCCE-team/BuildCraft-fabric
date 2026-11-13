package buildcraft.api.v2.guide;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** One top-level or nested section in the BuildCraft Guide Book. */
public final class GuideSection {
    private final Identifier id;
    private final String titleKey;
    private final Identifier icon;
    private final Identifier parent;
    private final int order;

    private GuideSection(Builder builder) {
        id = builder.id;
        titleKey = builder.titleKey;
        icon = builder.icon;
        parent = builder.parent;
        order = builder.order;
    }

    public static Builder builder(Identifier id, String titleKey) {
        return new Builder(id, titleKey);
    }

    public Identifier id() { return id; }
    public String titleKey() { return titleKey; }
    public Optional<Identifier> icon() { return Optional.ofNullable(icon); }
    public Optional<Identifier> parent() { return Optional.ofNullable(parent); }
    public int order() { return order; }

    public static final class Builder {
        private final Identifier id;
        private final String titleKey;
        private Identifier icon;
        private Identifier parent;
        private int order;

        private Builder(Identifier id, String titleKey) {
            this.id = Objects.requireNonNull(id, "id");
            this.titleKey = Objects.requireNonNull(titleKey, "titleKey");
            if (titleKey.isBlank()) throw new IllegalArgumentException("titleKey must not be blank");
        }

        public Builder icon(Identifier icon) { this.icon = Objects.requireNonNull(icon, "icon"); return this; }
        public Builder parent(Identifier parent) { this.parent = Objects.requireNonNull(parent, "parent"); return this; }
        public Builder order(int order) { this.order = order; return this; }
        public GuideSection build() { return new GuideSection(this); }
    }
}
