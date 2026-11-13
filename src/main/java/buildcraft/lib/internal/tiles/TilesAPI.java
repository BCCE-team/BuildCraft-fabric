package buildcraft.lib.internal.tiles;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

/**
 * Sided Fabric equivalents of BuildCraft's internal NeoForge block capability
 * identities. IDs and their logical values remain identical across loaders.
 * Module registration attaches providers to these lookups.
 */
public final class TilesAPI {
    public static final BlockApiLookup<IControllable, Direction> CAP_CONTROLLABLE =
        create("controllable", IControllable.class);
    public static final BlockApiLookup<IHasWork, Direction> CAP_HAS_WORK =
        create("has_work", IHasWork.class);
    public static final BlockApiLookup<IHeatable, Direction> CAP_HEATABLE =
        create("heatable", IHeatable.class);
    public static final BlockApiLookup<ITileAreaProvider, Direction> CAP_TILE_AREA_PROVIDER =
        create("tile_area_provider", ITileAreaProvider.class);

    private TilesAPI() {}

    private static <T> BlockApiLookup<T, Direction> create(String path, Class<T> type) {
        return BlockApiLookup.get(
            Identifier.fromNamespaceAndPath("buildcraftlib", path), type, Direction.class);
    }
}
