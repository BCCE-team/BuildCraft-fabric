package buildcraft.factory;

import buildcraft.lib.client.sprite.SpriteHolderRegistry;
import buildcraft.lib.client.sprite.SpriteHolderRegistry.SpriteHolder;
import net.minecraft.resources.Identifier;

public final class BCFactorySprites {
    public static final SpriteHolder pump_tube =
        SpriteHolderRegistry.getHolder("buildcraftfactory:blocks/pump/tube");
    public static final SpriteHolder mining_tube =
        SpriteHolderRegistry.getHolder("buildcraftfactory:blocks/mining_well/tube");

    public static final Identifier DISTILLER_POWER_A =
        Identifier.parse("buildcraftfactory:blocks/distiller/power_sprite_a");
    public static final Identifier DISTILLER_POWER_B =
        Identifier.parse("buildcraftfactory:blocks/distiller/power_sprite_b");
    public static final Identifier DISTILLER_POWER_C =
        Identifier.parse("buildcraftfactory:blocks/distiller/power_sprite_c");
    public static final Identifier DISTILLER_POWER_D =
        Identifier.parse("buildcraftfactory:blocks/distiller/power_sprite_d");

    public static final Identifier AUTO_BENCH_GUI =
        Identifier.parse("buildcraftfactory:textures/gui/autobench_item.png");
    public static final Identifier HEAT_EXCHANGE =
        Identifier.parse("buildcraftfactory:textures/gui/heat_exchanger.png");

    private BCFactorySprites() {
    }

    /** Forces holder registration before the first resource reload. */
    public static void init() {
    }
}
