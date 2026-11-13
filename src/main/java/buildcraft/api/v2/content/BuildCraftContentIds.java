package buildcraft.api.v2.content;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Stable ids for built-in content intended to be reused as addon archetypes/profiles. */
public final class BuildCraftContentIds {
    public static final class Machines {
        public static final Identifier QUARRY = id("buildcraftbuilders:quarry");
        public static final Identifier DISTILLER = id("buildcraftfactory:distiller");
        public static final Identifier MINING_WELL = id("buildcraftfactory:mining_well");
        public static final Identifier PUMP = id("buildcraftfactory:pump");

        private Machines() {}
    }

    public static final class Engines {
        public static final Identifier REDSTONE = id("buildcraftcore:engine_redstone");
        public static final Identifier STONE = id("buildcraftenergy:engine_stone");
        public static final Identifier IRON = id("buildcraftenergy:engine_iron");
        public static final Identifier CREATIVE = id("buildcraftcore:engine_creative");
        public static final Identifier FE = id("buildcraftenergy:engine_fe");
        /** MJ -> external-energy converter implemented with the engine chassis. */
        public static final Identifier MJ_DYNAMO = id("buildcraftenergy:mj_dynamo");

        private Engines() {}
    }

    public static final class EngineStages {
        public static final Identifier BLUE = id("buildcraft:engine_stage/blue");
        public static final Identifier GREEN = id("buildcraft:engine_stage/green");
        public static final Identifier YELLOW = id("buildcraft:engine_stage/yellow");
        public static final Identifier RED = id("buildcraft:engine_stage/red");
        public static final Identifier OVERHEAT = id("buildcraft:engine_stage/overheat");
        public static final Identifier BLACK = id("buildcraft:engine_stage/black");

        private EngineStages() {}
    }

    public static final class MachineComponents {
        public static final Identifier ENERGY = id("buildcraft:energy");
        public static final Identifier AREA = id("buildcraft:area");
        public static final Identifier MINING = id("buildcraft:mining");
        public static final Identifier PUMPING = id("buildcraft:pumping");
        public static final Identifier DISTILLATION = id("buildcraft:distillation");
        public static final Identifier INVENTORY_OUTPUT = id("buildcraft:inventory_output");
        public static final Identifier FLUID_INPUT = id("buildcraft:fluid_input");
        public static final Identifier FLUID_OUTPUT = id("buildcraft:fluid_output");
        public static final Identifier CHUNK_LOADING = id("buildcraft:chunk_loading");

        private MachineComponents() {}
    }

    public static final class LaserTables {
        public static final Identifier ASSEMBLY = id("buildcraftsilicon:assembly_table");
        public static final Identifier ADVANCED_CRAFTING = id("buildcraftsilicon:advanced_crafting_table");
        public static final Identifier INTEGRATION = id("buildcraftsilicon:integration_table");
        public static final Identifier CHARGING = id("buildcraftsilicon:charging_table");
        public static final Identifier PROGRAMMING = id("buildcraftsilicon:programming_table");

        private LaserTables() {}
    }

    public static final class Pipes {
        public static final Identifier STRUCTURE = id("buildcrafttransport:structure");

        public static final Identifier WOOD_ITEM = id("buildcrafttransport:wood_item");
        public static final Identifier WOOD_FLUID = id("buildcrafttransport:wood_fluid");
        public static final Identifier WOOD_POWER = id("buildcrafttransport:wood_power");
        public static final Identifier WOOD_FE = id("buildcrafttransport:wood_fe");

        public static final Identifier COBBLESTONE_ITEM = id("buildcrafttransport:cobblestone_item");
        public static final Identifier COBBLESTONE_FLUID = id("buildcrafttransport:cobblestone_fluid");
        public static final Identifier COBBLESTONE_POWER = id("buildcrafttransport:cobblestone_power");
        public static final Identifier COBBLESTONE_FE = id("buildcrafttransport:cobblestone_fe");

        public static final Identifier STONE_ITEM = id("buildcrafttransport:stone_item");
        public static final Identifier STONE_FLUID = id("buildcrafttransport:stone_fluid");
        public static final Identifier STONE_POWER = id("buildcrafttransport:stone_power");
        public static final Identifier STONE_FE = id("buildcrafttransport:stone_fe");

        public static final Identifier QUARTZ_ITEM = id("buildcrafttransport:quartz_item");
        public static final Identifier QUARTZ_FLUID = id("buildcrafttransport:quartz_fluid");
        public static final Identifier QUARTZ_POWER = id("buildcrafttransport:quartz_power");
        public static final Identifier QUARTZ_FE = id("buildcrafttransport:quartz_fe");

        public static final Identifier GOLD_ITEM = id("buildcrafttransport:gold_item");
        public static final Identifier GOLD_FLUID = id("buildcrafttransport:gold_fluid");
        public static final Identifier GOLD_POWER = id("buildcrafttransport:gold_power");
        public static final Identifier GOLD_FE = id("buildcrafttransport:gold_fe");

        public static final Identifier SANDSTONE_ITEM = id("buildcrafttransport:sandstone_item");
        public static final Identifier SANDSTONE_FLUID = id("buildcrafttransport:sandstone_fluid");
        public static final Identifier SANDSTONE_POWER = id("buildcrafttransport:sandstone_power");
        public static final Identifier SANDSTONE_FE = id("buildcrafttransport:sandstone_fe");

        public static final Identifier IRON_ITEM = id("buildcrafttransport:iron_item");
        public static final Identifier IRON_FLUID = id("buildcrafttransport:iron_fluid");
        public static final Identifier IRON_POWER = id("buildcrafttransport:iron_power");
        public static final Identifier IRON_FE = id("buildcrafttransport:iron_fe");

        public static final Identifier DIAMOND_ITEM = id("buildcrafttransport:diamond_item");
        public static final Identifier DIAMOND_FLUID = id("buildcrafttransport:diamond_fluid");
        public static final Identifier DIAMOND_POWER = id("buildcrafttransport:diamond_power");
        public static final Identifier DIAMOND_FE = id("buildcrafttransport:diamond_fe");

        public static final Identifier DIAMOND_WOOD_ITEM = id("buildcrafttransport:diamond_wood_item");
        public static final Identifier DIAMOND_WOOD_FLUID = id("buildcrafttransport:diamond_wood_fluid");
        public static final Identifier DIAMOND_WOOD_POWER = id("buildcrafttransport:diamond_wood_power");
        public static final Identifier DIAMOND_WOOD_FE = id("buildcrafttransport:diamond_wood_fe");

        public static final Identifier CLAY_ITEM = id("buildcrafttransport:clay_item");
        public static final Identifier CLAY_FLUID = id("buildcrafttransport:clay_fluid");
        public static final Identifier VOID_ITEM = id("buildcrafttransport:void_item");
        public static final Identifier VOID_FLUID = id("buildcrafttransport:void_fluid");
        public static final Identifier OBSIDIAN_ITEM = id("buildcrafttransport:obsidian_item");
        public static final Identifier LAPIS_ITEM = id("buildcrafttransport:lapis_item");
        public static final Identifier DAIZULI_ITEM = id("buildcrafttransport:daizuli_item");
        public static final Identifier EMZULI_ITEM = id("buildcrafttransport:emzuli_item");
        public static final Identifier STRIPES_ITEM = id("buildcrafttransport:stripes_item");

        private Pipes() {}
    }

    public static final class Worldgen {
        /** Standard BuildCraft oil-deposit generator profile. */
        public static final Identifier STANDARD_OIL = id("buildcraftenergy:oil");

        private Worldgen() {}
    }

    private BuildCraftContentIds() {
    }

    private static Identifier id(String value) {
        return Objects.requireNonNull(Identifier.tryParse(value));
    }
}
