package buildcraft.builders;

import buildcraft.lib.platform.registry.BCRegistryBinder;
import buildcraft.lib.platform.registry.BCRegistryEntry;
import buildcraft.lib.platform.registry.BCDeferredRegister;
import buildcraft.builders.block.BlockArchitectTable;
import buildcraft.builders.block.BlockBuilder;
import buildcraft.builders.block.BlockConstructionMarker;
import buildcraft.builders.block.BlockElectronicLibrary;
import buildcraft.builders.block.BlockFiller;
import buildcraft.builders.block.BlockFrame;
import buildcraft.builders.block.BlockQuarry;
import buildcraft.builders.block.BlockQuarryDrillCollision;
import buildcraft.builders.block.BlockReplacer;
import buildcraft.builders.tile.TileArchitectTable;
import buildcraft.builders.tile.TileBuilder;
import buildcraft.builders.tile.TileConstructionMarker;
import buildcraft.builders.tile.TileElectronicLibrary;
import buildcraft.builders.tile.TileFiller;
import buildcraft.builders.tile.TileQuarry;
import buildcraft.builders.tile.TileQuarryDrillCollision;
import buildcraft.builders.tile.TileReplacer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import buildcraft.lib.compat.BlockEntityTypeCompat;
import buildcraft.lib.compat.RegistryCompat;

public class BCBuildersBlocks {

    private static final BCDeferredRegister<Block> BLOCKS = BCDeferredRegister.create("minecraft:block", BCBuilders.MODID);
    public static final BCDeferredRegister<BlockEntityType<?>> BLOCK_ENTITYS = BCDeferredRegister.create("minecraft:block_entity_type", BCBuilders.MODID);

    public static final BCRegistryEntry<BlockFiller> FILLER = RegistryCompat.registerBlock(BLOCKS, "filler", BlockFiller::new);
    public static final BCRegistryEntry<BlockBuilder> BUILDER = RegistryCompat.registerBlock(BLOCKS, "builder", BlockBuilder::new);
    public static final BCRegistryEntry<BlockArchitectTable> ARCHITECT = RegistryCompat.registerBlock(BLOCKS, "architect", BlockArchitectTable::new);
    public static final BCRegistryEntry<BlockElectronicLibrary> LIBRARY = RegistryCompat.registerBlock(BLOCKS, "library", BlockElectronicLibrary::new);
    public static final BCRegistryEntry<BlockReplacer> REPLACER = RegistryCompat.registerBlock(BLOCKS, "replacer", BlockReplacer::new);
    public static final BCRegistryEntry<BlockConstructionMarker> CONSTRUCTION_MARKER = RegistryCompat.registerBlock(BLOCKS, "marker_construction", BlockConstructionMarker::new);

    public static final BCRegistryEntry<BlockFrame> FRAME = RegistryCompat.registerBlock(BLOCKS, "frame", BlockFrame::new);
    public static final BCRegistryEntry<BlockQuarry> QUARRY = RegistryCompat.registerBlock(BLOCKS, "quarry", BlockQuarry::new);
    public static final BCRegistryEntry<BlockQuarryDrillCollision> QUARRY_DRILL_COLLISION = RegistryCompat.registerBlock(BLOCKS, "quarry_drill_collision", BlockQuarryDrillCollision::new);


    public static final BCRegistryEntry<BlockEntityType<TileFiller>> FILLER_TILE_BC8 = BLOCK_ENTITYS.register("entity_filler",
            () -> BlockEntityTypeCompat.create(TileFiller::new, FILLER.get()));

    public static final BCRegistryEntry<BlockEntityType<TileBuilder>> BUILDER_TILE_BC8 = BLOCK_ENTITYS.register("entity_builder",
            () -> BlockEntityTypeCompat.create(TileBuilder::new, BUILDER.get()));

    public static final BCRegistryEntry<BlockEntityType<TileArchitectTable>> ARCHITECT_TILE_BC8 = BLOCK_ENTITYS.register("entity_architect",
            () -> BlockEntityTypeCompat.create(TileArchitectTable::new, ARCHITECT.get()));

    public static final BCRegistryEntry<BlockEntityType<TileElectronicLibrary>> LIBRARY_TILE_BC8 = BLOCK_ENTITYS.register("entity_library",
            () -> BlockEntityTypeCompat.create(TileElectronicLibrary::new, LIBRARY.get()));

    public static final BCRegistryEntry<BlockEntityType<TileReplacer>> REPLACER_TILE_BC8 = BLOCK_ENTITYS.register("entity_replacer",
            () -> BlockEntityTypeCompat.create(TileReplacer::new, REPLACER.get()));

    public static final BCRegistryEntry<BlockEntityType<TileConstructionMarker>> CONSTRUCTION_MARKER_TILE_BC8 = BLOCK_ENTITYS.register("entity_marker_construction",
            () -> BlockEntityTypeCompat.create(TileConstructionMarker::new, CONSTRUCTION_MARKER.get()));


    public static final BCRegistryEntry<BlockEntityType<TileQuarry>> QUARRY_TILE_BC8 = BLOCK_ENTITYS.register("entity_quarry",
            () -> BlockEntityTypeCompat.create(TileQuarry::new, QUARRY.get()));

    public static final BCRegistryEntry<BlockEntityType<TileQuarryDrillCollision>> QUARRY_DRILL_COLLISION_TILE_BC8 = BLOCK_ENTITYS.register("entity_quarry_drill_collision",
            () -> BlockEntityTypeCompat.create(TileQuarryDrillCollision::new, QUARRY_DRILL_COLLISION.get()));

    public static void registry(BCRegistryBinder b) {
        BLOCKS.register(b);
        BLOCK_ENTITYS.register(b);
    }
}
