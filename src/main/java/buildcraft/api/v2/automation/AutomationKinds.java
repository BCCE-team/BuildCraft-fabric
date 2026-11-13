package buildcraft.api.v2.automation;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public final class AutomationKinds {
    public static final Identifier BREAK_BLOCK = id("break_block");
    public static final Identifier PLACE_BLOCK = id("place_block");
    public static final Identifier USE_ITEM = id("use_item");
    public static final Identifier MOVE_ITEM = id("move_item");
    public static final Identifier MOVE_FLUID = id("move_fluid");

    private AutomationKinds() {}
    private static Identifier id(String path) { return Objects.requireNonNull(Identifier.tryParse("buildcraft:" + path)); }
}
