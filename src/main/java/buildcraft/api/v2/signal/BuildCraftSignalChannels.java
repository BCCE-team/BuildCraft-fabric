package buildcraft.api.v2.signal;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;

/** Stable IDs for the classic BuildCraft pipe-wire channels. */
public final class BuildCraftSignalChannels {
    public static final Identifier WHITE = id("white");
    public static final Identifier ORANGE = id("orange");
    public static final Identifier MAGENTA = id("magenta");
    public static final Identifier LIGHT_BLUE = id("light_blue");
    public static final Identifier YELLOW = id("yellow");
    public static final Identifier LIME = id("lime");
    public static final Identifier PINK = id("pink");
    public static final Identifier GRAY = id("gray");
    public static final Identifier LIGHT_GRAY = id("light_gray");
    public static final Identifier CYAN = id("cyan");
    public static final Identifier PURPLE = id("purple");
    public static final Identifier BLUE = id("blue");
    public static final Identifier BROWN = id("brown");
    public static final Identifier GREEN = id("green");
    public static final Identifier RED = id("red");
    public static final Identifier BLACK = id("black");

    private static final Map<DyeColor, Identifier> IDS = new EnumMap<>(DyeColor.class);

    static {
        IDS.put(DyeColor.WHITE, WHITE);
        IDS.put(DyeColor.ORANGE, ORANGE);
        IDS.put(DyeColor.MAGENTA, MAGENTA);
        IDS.put(DyeColor.LIGHT_BLUE, LIGHT_BLUE);
        IDS.put(DyeColor.YELLOW, YELLOW);
        IDS.put(DyeColor.LIME, LIME);
        IDS.put(DyeColor.PINK, PINK);
        IDS.put(DyeColor.GRAY, GRAY);
        IDS.put(DyeColor.LIGHT_GRAY, LIGHT_GRAY);
        IDS.put(DyeColor.CYAN, CYAN);
        IDS.put(DyeColor.PURPLE, PURPLE);
        IDS.put(DyeColor.BLUE, BLUE);
        IDS.put(DyeColor.BROWN, BROWN);
        IDS.put(DyeColor.GREEN, GREEN);
        IDS.put(DyeColor.RED, RED);
        IDS.put(DyeColor.BLACK, BLACK);
    }

    private BuildCraftSignalChannels() {}

    public static Identifier id(DyeColor color) {
        return Objects.requireNonNull(IDS.get(Objects.requireNonNull(color, "color")), "Missing signal channel id");
    }

    public static Optional<DyeColor> color(Identifier id) {
        Objects.requireNonNull(id, "id");
        for (Map.Entry<DyeColor, Identifier> entry : IDS.entrySet()) {
            if (entry.getValue().equals(id)) return Optional.of(entry.getKey());
        }
        return Optional.empty();
    }

    public static Map<DyeColor, Identifier> classicChannels() {
        return Map.copyOf(IDS);
    }

    private static Identifier id(String path) {
        return Objects.requireNonNull(Identifier.tryParse("buildcraft:" + path));
    }
}
