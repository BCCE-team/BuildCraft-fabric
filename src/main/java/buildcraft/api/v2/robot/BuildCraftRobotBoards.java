package buildcraft.api.v2.robot;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Stable identifiers for the built-in BuildCraft robot boards. */
public final class BuildCraftRobotBoards {
    public static final Identifier EMPTY = id("empty");
    public static final Identifier PICKER = id("picker");
    public static final Identifier CARRIER = id("carrier");
    public static final Identifier FLUID_CARRIER = id("fluid_carrier");
    public static final Identifier LUMBERJACK = id("lumberjack");
    public static final Identifier HARVESTER = id("harvester");
    public static final Identifier MINER = id("miner");
    public static final Identifier PLANTER = id("planter");
    public static final Identifier FARMER = id("farmer");
    public static final Identifier LEAVE_CUTTER = id("leave_cutter");
    public static final Identifier BUTCHER = id("butcher");
    public static final Identifier SHOVELMAN = id("shovelman");
    public static final Identifier PUMP = id("pump");
    public static final Identifier DELIVERY = id("delivery");
    public static final Identifier KNIGHT = id("knight");
    public static final Identifier BOMBER = id("bomber");
    public static final Identifier STRIPES = id("stripes");
    public static final Identifier BUILDER = id("builder");

    private BuildCraftRobotBoards() {}

    public static Identifier id(String key) {
        return Objects.requireNonNull(Identifier.tryParse("buildcraft:robot_board/" + key));
    }
}
