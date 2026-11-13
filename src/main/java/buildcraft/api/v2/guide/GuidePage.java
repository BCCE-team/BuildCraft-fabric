package buildcraft.api.v2.guide;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/**
 * Loader-neutral page model for the BuildCraft Guide Book.
 *
 * <p>The standard page records are intentionally data-like. Addons should prefer these over
 * client render callbacks so the same content can be supplied from code or datapacks.
 */
public interface GuidePage {
    Kind kind();

    enum Kind {
        TEXT,
        RECIPE,
        IMAGE,
        LINK,
        ITEM
    }

    /** Markdown/plain text. When translatable is true, value is a translation key. */
    record Text(String value, boolean translatable) implements GuidePage {
        public Text {
            Objects.requireNonNull(value, "value");
            if (value.isBlank()) throw new IllegalArgumentException("Guide text must not be blank");
        }
        public Kind kind() { return Kind.TEXT; }
    }

    record Recipe(Identifier recipeId) implements GuidePage {
        public Recipe { Objects.requireNonNull(recipeId, "recipeId"); }
        public Kind kind() { return Kind.RECIPE; }
    }

    record Image(Identifier texture, int width, int height, String captionKey) implements GuidePage {
        public Image {
            Objects.requireNonNull(texture, "texture");
            if (width <= 0 || height <= 0) throw new IllegalArgumentException("Image dimensions must be positive");
        }
        public Kind kind() { return Kind.IMAGE; }
    }

    record Link(Identifier targetEntry, String labelKey) implements GuidePage {
        public Link {
            Objects.requireNonNull(targetEntry, "targetEntry");
            Objects.requireNonNull(labelKey, "labelKey");
        }
        public Kind kind() { return Kind.LINK; }
    }

    record Item(Identifier itemId, String textKey) implements GuidePage {
        public Item {
            Objects.requireNonNull(itemId, "itemId");
            Objects.requireNonNull(textKey, "textKey");
        }
        public Kind kind() { return Kind.ITEM; }
    }
}
