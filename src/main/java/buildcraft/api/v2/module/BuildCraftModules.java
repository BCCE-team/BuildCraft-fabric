package buildcraft.api.v2.module;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public final class BuildCraftModules {
    public static final Identifier LIB = id("lib");
    public static final Identifier CORE = id("core");
    public static final Identifier TRANSPORT = id("transport");
    public static final Identifier ENERGY = id("energy");
    public static final Identifier FACTORY = id("factory");
    public static final Identifier BUILDERS = id("builders");
    public static final Identifier SILICON = id("silicon");
    public static final Identifier ROBOTICS = id("robotics");
    public static final Identifier COMPAT = id("compat");

    private BuildCraftModules() {}
    private static Identifier id(String path) {
        return Objects.requireNonNull(Identifier.tryParse("buildcraft:" + path));
    }
}
