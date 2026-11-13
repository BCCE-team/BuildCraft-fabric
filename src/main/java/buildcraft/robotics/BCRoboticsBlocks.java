package buildcraft.robotics;

import buildcraft.lib.platform.registry.BCRegistryBinder;
import buildcraft.lib.platform.registry.BCRegistryEntry;
import buildcraft.lib.platform.registry.BCDeferredRegister;
import buildcraft.robotics.block.BlockRequester;
import buildcraft.robotics.block.BlockZonePlanner;
import buildcraft.robotics.tile.TileRequester;
import buildcraft.robotics.tile.TileZonePlanner;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import buildcraft.lib.compat.BlockEntityTypeCompat;
import buildcraft.lib.compat.RegistryCompat;

public final class BCRoboticsBlocks {
    public static final BCDeferredRegister<Block> BLOCKS = BCDeferredRegister.create("minecraft:block", BCRobotics.MODID);
    public static final BCDeferredRegister<BlockEntityType<?>> BLOCK_ENTITYS = BCDeferredRegister.create("minecraft:block_entity_type", BCRobotics.MODID);

    public static final BCRegistryEntry<BlockZonePlanner> ZONE_PLANNER = RegistryCompat.registerBlock(BLOCKS, "zone_planner", BlockZonePlanner::new);
    public static final BCRegistryEntry<BlockRequester> REQUESTER = RegistryCompat.registerBlock(BLOCKS, "requester", BlockRequester::new);

    public static final BCRegistryEntry<BlockEntityType<TileZonePlanner>> ZONE_PLANNER_TILE = BLOCK_ENTITYS.register(
            "entity_zone_planner",
            () -> BlockEntityTypeCompat.create(TileZonePlanner::new, ZONE_PLANNER.get())
    );

    public static final BCRegistryEntry<BlockEntityType<TileRequester>> REQUESTER_TILE = BLOCK_ENTITYS.register(
            "entity_requester",
            () -> BlockEntityTypeCompat.create(TileRequester::new, REQUESTER.get())
    );

    private BCRoboticsBlocks() {
    }

    public static void registry(BCRegistryBinder bus) {
        BLOCKS.register(bus);
        BLOCK_ENTITYS.register(bus);
    }
}
