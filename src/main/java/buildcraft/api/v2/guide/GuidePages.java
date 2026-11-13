package buildcraft.api.v2.guide;

import net.minecraft.resources.Identifier;

/** Short factories for the standard Guide Book page types. */
public final class GuidePages {
    private GuidePages() {
    }

    public static GuidePage.Text text(String markdown) {
        return new GuidePage.Text(markdown, false);
    }

    public static GuidePage.Text textKey(String translationKey) {
        return new GuidePage.Text(translationKey, true);
    }

    public static GuidePage.Recipe recipe(Identifier recipeId) {
        return new GuidePage.Recipe(recipeId);
    }

    public static GuidePage.Image image(Identifier texture, int width, int height) {
        return new GuidePage.Image(texture, width, height, null);
    }

    public static GuidePage.Image image(Identifier texture, int width, int height, String captionKey) {
        return new GuidePage.Image(texture, width, height, captionKey);
    }

    public static GuidePage.Link link(Identifier targetEntry, String labelKey) {
        return new GuidePage.Link(targetEntry, labelKey);
    }

    public static GuidePage.Item item(Identifier itemId, String textKey) {
        return new GuidePage.Item(itemId, textKey);
    }
}
